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
        mTeam.getInBackground(id, new GetCallback<AVObject>() {
            @Override
            public void done(final AVObject mAVObject, AVException mE) {
                if ( mE == null){
                    String current_user_id = AVUser.getCurrentUser().getObjectId();
                    String team_name = mAVObject.getString(MTConfig.getTEAM_NAME());
                    String team_start_time = mAVObject.getString(MTConfig.getTEAM_START_TIME());
                    String team_plan = mAVObject.getString(MTConfig.getTEAM_DESCRIPTION());
                    String team_leader = mAVObject.getString(MTConfig.getTEAM_LEADER());
                    final List mPeople = mAVObject.getList(MTConfig.getTEAM_PEOPLE());
                    int need_people_num = mAVObject.getInt(MTConfig.getTEAM_NUM());
                    int current_people_num =  mPeople.size();
                    String team_num = getString(R.string.need_people_num_code) + need_people_num + getString(R.string.cuerrent_people_num_code) + current_people_num;
                    if ( need_people_num == current_people_num && current_user_id.equals(team_leader)){
                        mWantJoinBtn.setText(R.string.finish_code);
                    }else if( need_people_num == current_people_num ){
                        mWantJoinBtn.setText(R.string.team_is_on_code);
                    }
                    mInviteTeamName.setText(team_name);
                    mInviteTeamTime.setText(team_start_time);
                    mInviteTeamNum.setText(team_num);
                    mInviteTeamPlan.setText(team_plan);
                    mWantJoinBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View mView) {
                            if (mWantJoinBtn.getText().equals("完成任务")){

                            }else if (mWantJoinBtn.getText().equals("小组正在进行中")){

                            }else {

                            }
                        }
                    });
                }
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
