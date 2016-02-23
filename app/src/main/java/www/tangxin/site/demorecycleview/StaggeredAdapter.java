package www.tangxin.site.demorecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 89742 on 21-Feb-16 0021.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;
    private List<Integer> mHeights;

    public StaggeredAdapter(Context context, List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<Integer>();
        for(int i=0; i <mDatas.size() ; i++){ //产生随机宽度100-400px
            this.mHeights.add((int) (100+Math.random()*400));
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.id_textView);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position); //设置随机的宽度
        holder.itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
