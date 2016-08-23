package no.txcb.sample.comments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import no.txcb.sample.R;
import no.txcb.sample.databinding.ActivityCommentsBinding;

public class CommentsActivity extends AppCompatActivity implements CommentsView {

    private ActivityCommentsBinding binding;
    private RecyclerView commentsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        CommentsPresenter commentsPresenter = new CommentsPresenter();
        commentsPresenter.attachView(this);
        commentsPresenter.loadComments();
        commentsRecyclerView = binding.commentsContent.commentsRecycler;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showComments(List<String> comments) {
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsRecyclerView.setAdapter(new SimpleTextAdapter(comments));
    }

    @Override
    public void setError(String error) {

    }
}
