package no.txcb.sample.common;

import android.databinding.ObservableField;

public class TextListItem {
    public final ObservableField<String> text = new ObservableField<>();
    public final ObservableField<Long> id = new ObservableField<>();
}
