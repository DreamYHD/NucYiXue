package androidlab.edu.cn.nucyixue.ui.xuanshangPack;

import android.content.Context;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidlab.edu.cn.nucyixue.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dreamY on 2017/8/31.
 */

public class XunshangSendImageAdapter extends RecyclerView.Adapter<XunshangSendImageAdapter.ViewHolder> {

    private int ITEM_TYPE_NORMAL = 0;
    private int ITEM_TYPE_FOOTER = 1;
    private int ITEM_TYPE_EMPTY = 2;

    private List<String> mStringList = new ArrayList<>();
    private Context mContext;
    private View mFooterView;
    private View mEmptyView;

    public void setFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void setEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
        notifyDataSetChanged();
    }

    private OnReClickerListener mOnReClickerListener;
    public interface OnReClickerListener{
        void click(View mView,int position);
    }

    public void setOnclickerListener(OnReClickerListener mOnclickerListener){
        this.mOnReClickerListener = mOnclickerListener;
    }
    public XunshangSendImageAdapter(List<String> mStringList, Context mContext) {
        this.mStringList = mStringList;
        this.mContext = mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_EMPTY) {
            return new ViewHolder(mEmptyView);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        } else {
            View v = LayoutInflater.from(mContext)
                    .inflate(
                            R.layout.activity_xuanshang_send_image_item,
                            parent,
                            false);
            return new ViewHolder(v);
        }

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (       type == ITEM_TYPE_FOOTER
                || type == ITEM_TYPE_EMPTY) {
            if (mOnReClickerListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        mOnReClickerListener.click(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }
        }
    }
    @Override
    public int getItemCount() {
        int itemCount = mStringList.size();
        if (null != mEmptyView && itemCount == 0) {
            itemCount++;
        }
        if (null != mFooterView) {
            itemCount++;
        }
        return itemCount;
    }
    @Override
    public int getItemViewType(int position) {
        if (null != mFooterView
                && position == getItemCount() - 1) {
            return ITEM_TYPE_FOOTER;
        }
        if (null != mEmptyView && mStringList.size() == 0){
            return ITEM_TYPE_EMPTY;
        }
        return ITEM_TYPE_NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mEmptyView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            mImageView = itemView.findViewById(R.id.xuanshang_send_image_item);
        }
    }
}
