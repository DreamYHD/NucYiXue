package androidlab.edu.cn.nucyixue.ui.xuanshangPack;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.base.BaseActivity;
import androidlab.edu.cn.nucyixue.utils.FlexTextUtil;
import butterknife.BindView;
import butterknife.OnClick;

public class XuanshangSendActivity extends BaseActivity {

    @BindView(R.id.xuanshang_back_img)
    ImageView mXuanshangBackImg;
    @BindView(R.id.xuanshang_send_location_image)
    ImageView mXuanshangSendLocationImage;
    @BindView(R.id.xuanshang_send_btn)
    TextView mXuanshangSendBtn;
    @BindView(R.id.xuanshang_edit_show)
    EditText mXuanshangEditShow;
    @BindView(R.id.xuanshang_image_recycler)
    RecyclerView mXuanshangImageRecycler;
    @BindView(R.id.xuanshang_send_location_text)
    TextView mXuanshangSendLocationText;
    @BindView(R.id.xuanshang_send_money_edit)
    TextView mXuanshangSendMoneyEdit;
    private XunshangSendImageAdapter  mXunshangSendImageAdapter;
    private GridLayoutManager mGridLayoutManager;

    private List<String>mStringList = new ArrayList<>();
    @Override
    protected void logicActivity(Bundle mSavedInstanceState) {
        mGridLayoutManager = new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false);
        mXuanshangImageRecycler.setLayoutManager(mGridLayoutManager);
        mXunshangSendImageAdapter = new XunshangSendImageAdapter(mStringList,this);
        mXunshangSendImageAdapter.setOnclickerListener(new XunshangSendImageAdapter.OnReClickerListener() {
            @Override
            public void click(View mView, int position) {
                Dialog mDialog = new Dialog(XuanshangSendActivity.this, R.style.BottomDialog);
                View mBottom = LayoutInflater.from(mActivity).inflate(R.layout.dialog_content_circle, null);
                mDialog.setContentView(mBottom);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mBottom.getLayoutParams();
                params.width = getResources().getDisplayMetrics().widthPixels - FlexTextUtil.dp2px(mActivity, 16f);
                params.bottomMargin = FlexTextUtil.dp2px(mActivity, 8f);
                mBottom.setLayoutParams(params);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                mDialog.show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xuanshang_send;
    }


    @OnClick({R.id.xuanshang_back_img, R.id.xuanshang_send_location_image,R.id.xuanshang_send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xuanshang_back_img:
                mActivity.finish();
                break;
            case R.id.xuanshang_send_btn:

                break;
            case R.id.xuanshang_send_location_image:
                break;
        }
    }

}
