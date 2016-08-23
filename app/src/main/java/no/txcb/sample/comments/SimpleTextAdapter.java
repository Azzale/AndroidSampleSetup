package no.txcb.sample.comments;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import no.txcb.sample.R;
import no.txcb.sample.common.TextListItem;
import no.txcb.sample.databinding.SimpleTextItemBinding;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.SimpleTextHolder> {

    private List<String> strings;

    public SimpleTextAdapter(List<String> strings) {
        super();
        this.strings = strings;
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleTextItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.simple_text_item, parent, false);
        return new SimpleTextHolder(binding);
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, int position) {
        holder.textListItem.text.set(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class SimpleTextHolder extends RecyclerView.ViewHolder {


        private final TextListItem textListItem;

        SimpleTextHolder(SimpleTextItemBinding itemView) {
            super(itemView.getRoot());
            textListItem = new TextListItem();
            itemView.setItem(textListItem);
        }


    }
}
