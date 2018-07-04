package com.example.administrator.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.test.CommentsInfo;
import com.example.administrator.test.R;
import com.example.administrator.test.adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends Activity implements CommentAdapter.OnStatusListener{
    ArrayList<CommentsInfo> list = new ArrayList<>();
    CommentAdapter commentAdapter;
    RecyclerView lvComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        findViews();
    }

    protected void findViews() {
        lvComment = findViewById(R.id.lv_comments);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        lvComment.setLayoutManager(layoutmanager);

        for(int i=0;i<3;i++){
            CommentsInfo commentsInfo = new CommentsInfo();
            List<String> commentImgs = new ArrayList<>();
            commentImgs.add("");
            commentsInfo.setCommentImgs(commentImgs);
            list.add(commentsInfo);
        }
        commentAdapter = new CommentAdapter(list,this);
        commentAdapter.setOnStatusListener(this);
        lvComment.setAdapter(commentAdapter);
    }

    @Override
    public void onSetStatusListener(int pos) {

            if(list.get(pos).getCommentImgs().size()<5){
                CommentsInfo commentsInfo = list.get(pos);
                List<String> commentImgs = commentsInfo.getCommentImgs();
                commentImgs.add(0,"http://img.xsmore.com/cjyp/Product/2ef24a07-f3b0-4497-8549-2acc0f7ca4b0.jpg");
                commentsInfo.setCommentImgs(commentImgs);
                list.set(pos,commentsInfo);
                commentAdapter.notifyItemChanged(pos);
            }else if(list.get(pos).getCommentImgs().size()==5){
                CommentsInfo commentsInfo = list.get(pos);
                List<String> commentImgs = commentsInfo.getCommentImgs();
                commentImgs.set(commentImgs.size()-1,"http://img.xsmore.com/cjyp/Product/2ef24a07-f3b0-4497-8549-2acc0f7ca4b0.jpg");
                commentsInfo.setCommentImgs(commentImgs);
                list.set(pos,commentsInfo);
                commentAdapter.notifyItemChanged(pos);
            }
    }

    @Override
    public void onDeleteListener(int pos,int tagPos) {
        CommentsInfo commentsInfo = list.get(pos);
        List<String> commentImgs = commentsInfo.getCommentImgs();
        if(!commentImgs.get(commentImgs.size()-1).equals("")){
            commentImgs.add("");
        }
        commentImgs.remove(tagPos);
        commentsInfo.setCommentImgs(commentImgs);
        list.set(pos,commentsInfo);
        commentAdapter.notifyItemChanged(pos);
    }

}
