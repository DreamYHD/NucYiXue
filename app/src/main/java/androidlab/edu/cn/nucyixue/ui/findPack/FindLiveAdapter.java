package androidlab.edu.cn.nucyixue.ui.findPack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.base.BaseRecyclerAdapter;
import androidlab.edu.cn.nucyixue.base.BaseViewHolder;
import androidlab.edu.cn.nucyixue.data.bean.LiveBean;
import butterknife.ButterKnife;

/**
 * Created by dreamY on 2017/7/27.
 */

public class FindLiveAdapter extends BaseRecyclerAdapter<LiveBean> {


    public FindLiveAdapter(int mLayoutId, Context mContext, List<LiveBean> mLiveBeen) {
        super(mLayoutId, mContext, mLiveBeen);
    }

    @Override
    protected void onBind(BaseViewHolder mHolder, LiveBean mLiveBean, int mPosition) {

    }
}
