package com.example.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.CommentsInfo;
import com.example.administrator.test.R;
import com.example.administrator.test.flowtaglayout.FlowTagLayout;
import com.example.administrator.test.flowtaglayout.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company:
 * @Created: 2018/6/6
 * 作者：wy
 * @Description:
 * @version: 1.0
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private ArrayList<CommentsInfo> list;
    private Context context;
    private OnStatusListener onStatusListener;
    public CommentAdapter(ArrayList<CommentsInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_comment, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.flComment
                .setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        TagAdapter<String> mSizeTagAdapter1 = new TagAdapter<>(context);
        viewHolder.flComment.setAdapter(mSizeTagAdapter1);
        mSizeTagAdapter1.onlyAddAll(list.get(i).getCommentImgs());

        viewHolder.flComment.setOnTagSelectListener(new OnTagSelectListener() {

            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if(list.get(i).getCommentImgs().get(selectedList.get(0)).equals("")){
                    onStatusListener.onSetStatusListener(i);
                }else{
                    onStatusListener.onDeleteListener(i,selectedList.get(0));
                }
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        FlowTagLayout flComment;

        public ViewHolder(View itemView) {
            super(itemView);
            flComment = itemView.findViewById(R.id.fl_comment);
        }
    }

    public interface OnStatusListener {
        void onSetStatusListener(int pos);
        void onDeleteListener(int pos, int tagPos);
    }
}
