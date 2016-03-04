package br.com.mgalhardo.guidebook.ui.guidelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mgalhardo.guidebook.R;
import br.com.mgalhardo.guidebook.core.entity.Guide;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.ViewHolder> {

    private Context context;
    private List<Guide> guides;

    public GuideListAdapter(Context context, List<Guide> guides) {
        this.context = context;
        this.guides = guides;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guide, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Guide guide = guides.get(position);
        if (guide != null) {
            if (!TextUtils.isEmpty(guide.icon)) {
                Picasso.with(context)
                        .load(guide.icon)
                        .into(holder.icon);
            }
            holder.name.setText(guide.name);
            if (TextUtils.isEmpty(guide.venue.city)) {
                holder.city.setVisibility(View.GONE);
            } else {
                holder.city.setText(guide.venue.city);
            }
            if (TextUtils.isEmpty(guide.venue.state)) {
                holder.state.setVisibility(View.GONE);
            } else {
                holder.state.setText(guide.venue.state);
            }
            holder.endDate.setText(guide.endDate);
        }
    }

    @Override
    public int getItemCount() {
        return guides.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.icon)
        ImageView icon;

        @Bind(R.id.name)
        TextView name;

        @Bind(R.id.city)
        TextView city;

        @Bind(R.id.state)
        TextView state;

        @Bind(R.id.end_date)
        TextView endDate;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

}
