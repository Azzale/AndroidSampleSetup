package no.txcb.sample.comments;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import no.txcb.sample.R;
import no.txcb.sample.comments.models.Comment;
import no.txcb.sample.common.TextListItem;
import no.txcb.sample.databinding.SimpleTextItemBinding;

public class SimpleCommentAdapter extends RecyclerView.Adapter<SimpleCommentAdapter.SimpleTextHolder> {

    private final CommentsView commentsView;
    private List<Comment> comments;

    public SimpleCommentAdapter(CommentsView view, List<Comment> strings) {
        super();
        this.comments = strings;
        this.commentsView = view;
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleTextItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.simple_text_item, parent, false);

        return new SimpleTextHolder(binding);
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, int position) {
        final Comment comment = comments.get(position);
        holder.textListItem.text.set(comment.body);
        holder.textListItem.id.set(comment.id);
        holder.itemView.text.setOnClickListener(view -> commentsView.onDeleteCommentRequested(comment.id));

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class SimpleTextHolder extends RecyclerView.ViewHolder{
        final TextListItem textListItem;
        SimpleTextItemBinding itemView;

        SimpleTextHolder(SimpleTextItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            textListItem = new TextListItem();
            itemView.setItem(textListItem);
        }
    }
}
