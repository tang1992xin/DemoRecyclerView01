package www.tangxin.site.demorecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 89742 on 21-Feb-16 0021.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListerner;

    public void setOnItemClickListerner(OnItemClickListener listerner){
        this.mOnItemClickListerner = listerner;
    }

    public SimpleAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.id_textView);
        }
    }
    public void addData(int position){
        mDatas.add(position,"insert one");
      //  notifyDataSetChanged();
        notifyItemInserted(position);


    }
    public void deleteData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        if(mOnItemClickListerner != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition =  holder.getLayoutPosition();//当前item位置 //插入了新的item位置变了所以要获取
                    if (mOnItemClickListerner != null) {
                        mOnItemClickListerner.onItemClick(holder.itemView, layoutPosition);//
                    }
                }
            });
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int layoutPosition =   holder.getLayoutPosition();
                mOnItemClickListerner.onItemLongClick(holder.itemView,layoutPosition);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
