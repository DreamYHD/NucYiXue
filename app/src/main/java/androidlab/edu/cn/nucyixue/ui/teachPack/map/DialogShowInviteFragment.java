package androidlab.edu.cn.nucyixue.ui.teachPack.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.List;

import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.utils.config.MTConfig;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dreamY on 2017/9/8.
 */

public class DialogShowInviteFragment extends DialogFragment {
    @BindView(R.id.invite_team_name)
    TextView mInviteTeamName;
    @BindView(R.id.invite_team_num)
    TextView mInviteTeamNum;
    @BindView(R.id.invite_team_time)
    TextView mInviteTeamTime;
    @BindView(R.id.invite_team_plan)
    TextView mInviteTeamPlan;
    @BindView(R.id.want_join_btn)
    Button mWantJoinBtn;
    Unbinder unbinder;
    private String id;

    public DialogShowInviteFragment(String mId) {
        id = mId;
    }

    public static DialogShowInviteFragment getInstance(String mId) {
        return new DialogShowInviteFragment(mId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_dialog_show_invite, container);
        unbinder = ButterKnife.bind(this, mView);
        logic();
        return mView;
    }

    private void logic() {
        AVQuery<AVObject> mTeam = new AVQuery<>(MTConfig.getTABLE_NAME());
        mTeam.whereEqualTo(MTConfig.getTABLE_ID(), id);

        mTeam.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(final AVObject mAVObject, AVException mE) {

                String team_name = mAVObject.getString(MTConfig.getTEAM_NAME());
                String team_start_time = mAVObject.getString(MTConfig.getTEAM_START_TIME());
                String team_plan = mAVObject.getString(MTConfig.getTEAM_DESCRIPTION());
                final List mPeople = mAVObject.getList(MTConfig.getTEAM_PEOPLE());
                String team_num = "需要人数" + mAVObject.get(MTConfig.getTEAM_NUM()) + "当前人数 " + mPeople.size();
                mInviteTeamName.setText(team_name);
                mInviteTeamTime.setText(team_start_time);
                mInviteTeamNum.setText(team_num);
                mInviteTeamPlan.setText(team_plan);
                mWantJoinBtn.setOnClickListener(new View.OnClickListener() {
                    List mList = mPeople;
                    @Override
                    public void onClick(View mView) {
                        Boolean isOn = false;
                        for (Object mS : mPeople) {
                            if (AVUser.getCurrentUser().getObjectId().equals(mS)) {
                                isOn = true;
                            }
                        }
                        if (isOn) {
                            Toast.makeText(getContext(), "你已经加入小组，不需要重复加入~", Toast.LENGTH_SHORT).show();
                        } else if (mPeople.size() == mAVObject.getInt(MTConfig.getTEAM_NUM())) {
                            Toast.makeText(getContext(), "小组人数已经满了哦~", Toast.LENGTH_SHORT).show();
                        } else {
                            AVObject mTeam = AVObject.createWithoutData(MTConfig.getTABLE_NAME(), id);
                            mList.add("test");
                            mTeam.put(MTConfig.getTEAM_PEOPLE(), mList);
                            mTeam.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(AVException mE) {
                                    if (mE == null) {
                                        Toast.makeText(getContext(), "加入小组成功，快去完成任务吧~", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /*
    *设置全屏
    */
    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
    }
}
