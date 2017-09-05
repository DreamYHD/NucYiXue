package androidlab.edu.cn.nucyixue.ui.xuanshangPack;

import android.content.Context;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.base.BaseRecyclerAdapter;
import androidlab.edu.cn.nucyixue.base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dreamY on 2017/8/31.
 */

public class XunshangSendImageAdapter extends BaseRecyclerAdapter<String> {

    private static final String TAG = "XunshangSendImageAdapte";
    private Context mContext;
    public XunshangSendImageAdapter(int mLayoutId, Context mContext, List<String> mStrings) {
        super(mLayoutId, mContext, mStrings);
        this.mContext = mContext;
    }
    @Override
    protected void onBind(BaseViewHolder mHolder, String mS, int mPosition) {
        Log.i(TAG, "onBind: "+mS);
        Glide.with(mContext)
                .load(mS)
                .into((ImageView) mHolder.getView(R.id.xuanshang_send_image_item));
    }
}
