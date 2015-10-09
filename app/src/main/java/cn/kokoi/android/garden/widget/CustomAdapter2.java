/*
package cn.kokoi.android.garden.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.kokoi.android.garden.R;

*/
/**
 * Created by zkl on 2015/4/24.
 *//*

public class CustomAdapter2 extends BaseAdapter {

    private Context context;
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;
    private CellSoundListener listener;

    public final class ListItemView {
        public TextView title;
        public LinearLayout soundListLayout;
    }

    public CustomAdapter2(Context context, List<Map<String, Object>> listItems, CellSoundListener listener) {
        this.context = context;
        this.listItems = listItems;
        this.listContainer = LayoutInflater.from(this.context);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;
        if (convertView == null){
            listItemView = new ListItemView();
            //获取 layout_listen_list 的布局文件的视图
            convertView = listContainer.inflate(R.layout.layout_custom_list2,null);
            //获取控件对象
            listItemView.title = (TextView)convertView.findViewById(R.id.layout_listen_list_textView);

            listItemView.soundListLayout = (LinearLayout)convertView.findViewById(R.id.soundListLayout);

            FixGridLayout customView = new FixGridLayout(context);
            customView.setCellWidth(100);
            customView.setCellHeight(60);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            listItemView.soundListLayout.addView(customView, params);

            Map<String, Object> item = listItems.get(position);
            listItemView.title.setText((String)item.get("name"));
            List<String> sounds = (List<String>)item.get("sounds");

            System.out.println("=================================" + convertView.getWidth());

            //动态添加控件
            for(int i = 0; i < sounds.size(); i++){
                RelativeLayout layout_practise_item_cell = (RelativeLayout)listContainer.inflate(R.layout.layout_practise_item_cell, null);
                layout_practise_item_cell.setTag("layout_practise_item_cell" + (i + 1));
                RelativeLayout.LayoutParams cellParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                customView.addView(layout_practise_item_cell, cellParams);

                TextView idText = (TextView)layout_practise_item_cell.findViewById(R.id.idTextView);
                idText.setText("" + (i+1) + ". ");
                ImageButton btn = (ImageButton)layout_practise_item_cell.findViewById(R.id.imageButton3);

                CellInfo cellTag = new CellInfo();
                cellTag.setListPosition(position);
                cellTag.setCellPosition(i);
                cellTag.setSoundPath(sounds.get(i));

                btn.setTag(cellTag);

                btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            v.setBackgroundResource(R.drawable.player_flag_highlight);
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            v.setBackgroundResource(R.drawable.player_flag_normal);
                        } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                            v.setBackgroundResource(R.drawable.player_flag_normal);
                        }
                        return false;
                    }

                });
                btn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.play(((CellInfo)v.getTag()).getSoundPath());
                    }

                });
            }
            //设置控件集到 convertView
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView)convertView.getTag();
        }


        return convertView;
    }
}
*/
