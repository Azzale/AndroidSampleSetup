package no.txcb.sample.comments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import java.util.List;

import no.txcb.sample.R;
import no.txcb.sample.comments.models.Comment;
import no.txcb.sample.databinding.ActivityCommentsBinding;

public class CommentsActivity extends AppCompatActivity implements CommentsView {

    private ActivityCommentsBinding binding;
    private RecyclerView commentsRecyclerView;
    private CommentsPresenter commentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        commentsPresenter = new CommentsPresenter();
        commentsPresenter.attachView(this);
        commentsPresenter.loadComments();
        commentsRecyclerView = binding.commentsContent.commentsRecycler;

        binding.addComment.setOnEditorActionListener((textView, actionId, event) -> {
            if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                postComment(textView.getText().toString());
                textView.setText("");
            }
            return false;
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsRecyclerView.setAdapter(new SimpleCommentAdapter(this, comments));
    }

    @Override
    public void postComment(String comment) {
        commentsPresenter.postComment(comment);
    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void onCommentAdded(Comment comment) {
        // call to refresh everything instead of adding the one comment
        commentsPresenter.loadComments();
    }

    @Override
    public void onDeleteCommentRequested(final long id) {
        // display "are you sure" popup
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete this comment?");

        builder.setPositiveButton("YES", (dialog, which) -> {
            commentsPresenter.deleteComment(id);
            // Do nothing but close the dialog
            dialog.dismiss();
        });

        builder.setNegativeButton("NO", (dialog, which) -> {
            // Do nothing
            dialog.dismiss();
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
