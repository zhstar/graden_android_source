package cn.kokoi.android.garden.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.models.menu.Item;
import cn.kokoi.android.garden.models.menu.Menu;
import cn.kokoi.android.garden.models.util.GradeFactory;

/**
 * Created by zkl on 2015/5/20.
 */
public abstract class BookMenuBaseFragment extends Fragment {


    protected abstract Intent getIntent(int itemPosition);

    protected abstract Class<?> getIntentActivityClass();


    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected Menu mMenu;
    protected int mBookId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mLayoutInflater = LayoutInflater.from(mContext);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_menu_tabs_reading_practise, container, false);

        ViewGroup menu_container = (ViewGroup)v.findViewById(R.id.reading_item_container);

        if (mMenu != null){
            int totalItems = mMenu.getItems().size();
            for (int i=0; i < totalItems; i++){
                Item item = mMenu.getItems().get(i);
                addButton(menu_container, item);
            }
        }

        return v;
    }

    protected void addButton(ViewGroup container, final Item item) {

        View newButton = mLayoutInflater.inflate(R.layout.layout_menu_item, null);

        TextView title = (TextView)newButton.findViewById(R.id.unit_item_title_textView);
        ImageView bg = (ImageView)newButton.findViewById(R.id.unit_item_bg_imageView);


        //获取单元菜单的底图
        Bitmap bitmap = getLocalBitmap(mContext, GradeFactory.getBookDirNameById(mBookId) + "/" + item.getBgImg());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //获取配置文件里的高度
        int destHeight = (int)getResources().getDimension(R.dimen.menu_item_height);
        //计数高度放大的比例
        float per = (float)dp2px(mContext, destHeight) / height;
        //Trace.print("width = " + width + ", height = " + height + ", per = " + per);
        //定义缩放图片的矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(per, per);
        //根据矩阵重新生成新的底图
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0,0, width, height, matrix, true);
        //
        bg.setImageBitmap(newBitmap);

        title.setText(item.getContent());
        container.addView(newButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = mMenu.getItems().indexOf(item);
                Intent intent = getIntent(index);
                /*int index = mUnitItem.getItems().indexOf(item);
                intent.putExtra(BookReadingFragment.EXTRA_BOOK_ID, mBookId);
                intent.putExtra(BookReadingFragment.EXTRA_UNIT_NAME, item.getName());
                intent.putExtra(BookReadingFragment.EXTRA_UNIT_TITLE, item.getContent());
                Bundle bundle = new Bundle();
                bundle.putSerializable(BookMenuBaseFragment.EXTRA_ARGS, getSerializableObject());
                intent.putExtras(bundle);*/
                intent.setClass(getActivity(), getIntentActivityClass());
                startActivity(intent);
            }
        });
        newButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        v.setScaleX(1.05f);
                        v.setScaleY(1.05f);
                    }else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
                        v.setScaleX(1.0f);
                        v.setScaleY(1.0f);
                    }
                }

                return false;
            }
        });
    }

    protected Bitmap getLocalBitmap(Context context, String url) {
        try{
            InputStream is = context.getApplicationContext().getAssets().open(url);
            return BitmapFactory.decodeStream(is);
        }catch(Exception e){

        }
        return null;
    }

    //把dp转成px
    protected int dp2px(Context context, int dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
