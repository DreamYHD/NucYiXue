package androidlab.edu.cn.nucyixue.ui.findPack;

import android.content.Context;

import java.util.List;

import androidlab.edu.cn.nucyixue.base.BaseRecyclerAdapter;
import androidlab.edu.cn.nucyixue.base.BaseViewHolder;
import androidlab.edu.cn.nucyixue.data.bean.Live;
import io.reactivex.Observable;

/**
 * Created by dreamY on 2017/7/27.
 */

public class FindLiveAdapter extends BaseRecyclerAdapter<Live> {


    public FindLiveAdapter(int mLayoutId, Context mContext, List<Live> mLiveBeen) {
        super(mLayoutId, mContext, mLiveBeen);
    }

    @Override
    protected void onBind(BaseViewHolder mHolder, Live mLiveBean, int mPosition) {

    }
}
