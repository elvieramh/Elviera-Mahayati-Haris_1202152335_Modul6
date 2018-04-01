package android.elviera.com.elviera_1202152335_modul6;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private List<CommentModel> list;

    public CommentListAdapter(List<CommentModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentModel item = list.get(position);
        holder.bindTo(item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lblUsername, lblCommentText;

        public ViewHolder(View itemView) {
            super(itemView);
            lblUsername=(TextView)itemView.findViewById(R.id.lblCommentUser);
            lblCommentText=(TextView)itemView.findViewById(R.id.lblCommentTXT);
        }

        public void bindTo(CommentModel model){
            CommentModel curr = model;
            lblUsername.setText(curr.getUser());
            lblCommentText.setText(curr.getIsi());
        }

    }
}
