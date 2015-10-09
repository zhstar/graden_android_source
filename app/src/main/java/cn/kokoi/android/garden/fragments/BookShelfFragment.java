package cn.kokoi.android.garden.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.kokoi.android.garden.BookMenuActivity;
import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.models.BookShelfItem;
import cn.kokoi.android.garden.models.update.UpdateManager;
import cn.kokoi.android.garden.models.util.GradeFactory;
import cn.kokoi.android.garden.widget.BookShelfGridView;
import cn.kokoi.android.garden.widget.TitleBarManager;

/**
 * Created by zkl on 2015/5/12.
 */
public class BookShelfFragment extends Fragment {

    public static BookShelfFragment newInstance() {
        BookShelfFragment fragment = new BookShelfFragment();
        return fragment;
    }



    private ArrayList<String> mBookFolders = new ArrayList<>();


    private UpdateManager mUpdateManager;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UpdateManager.OBTAIN_VERSION_SUCCESS){
                if (mUpdateManager.doesExistNewVersion(mUpdateManager.getUpdateInfo())){
                    mUpdateManager.showUpdateDialog();
                }
            }

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetManager assetManager = getActivity().getAssets();

        String[] folders = null;
        try{
            folders = assetManager.list("");
            for (String book:folders){
                if (book.toLowerCase().indexOf("book") != -1){
                    //Trace.print(book);
                    mBookFolders.add(book);
                }
            }
            Collections.sort(mBookFolders, new SortBookName());
            /*Trace.print("+++++++++++++++");
            for (String b:mBookFolders){
                Trace.print(b);
            }*/
        }catch (Exception e){}


        String url = getResources().getString(R.string.update_version_xml);
        mUpdateManager = UpdateManager.getInstance(getActivity());
        mUpdateManager.setHandler(mHandler);

        mUpdateManager.initWithUrl(url);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bookshelf, container, false);

        TitleBarManager titleBarManager = new TitleBarManager(v, R.id.bookshelf_titlebar);
        titleBarManager.getLeftButton().setVisibility(View.GONE);
        titleBarManager.setTitle(R.string.bookshelf_title);


        final ArrayList<BookShelfItem> items = new ArrayList<BookShelfItem>();
        //items.add(new Book(5, R.drawable.book_item_bg1));

        for (int i = 0; i < mBookFolders.size(); i++){
            String b = mBookFolders.get(i);
            int id = Integer.parseInt(b.substring(4, b.length()));
            BookShelfItem bookShelfItem = new BookShelfItem();
            bookShelfItem.setId(id);
            bookShelfItem.setName(b);
            items.add(bookShelfItem);
        }

        /*for (int i = 0; i< mBookItemBgs.length; i++){
            items.add(new Book(5, mBookItemBgs[i]));
        }*/

        BookShelfGridView gridView = (BookShelfGridView)v.findViewById(R.id.bookShelf_gridView);
        gridView.setAdapter(new BookShelfAdapter(getActivity(), items));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                //Log.d("d", ""+items.get(position).getId());
                i.putExtra(BookMenuFragment.EXTRA_BOOK_ID, items.get(position).getId());
                i.setClass(getActivity(), BookMenuActivity.class);
                startActivity(i);
            }
        });

        return v;
    }



    /**
     *
     */
    private class BookShelfAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<BookShelfItem> mBookShelfItems;

        public BookShelfAdapter(Context context, ArrayList<BookShelfItem> bookShelfItems) {
            mContext = context;
            mBookShelfItems = bookShelfItems;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.layout_bookshelf_item, null);
            }

            TextView title = (TextView)convertView.findViewById(R.id.bookshelf_item_title_textView);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.bookshelf_item_thumbnail_imageView);
            BookShelfItem bookShelfItem = mBookShelfItems.get(position);
            String bookImg = bookShelfItem.getName() + "/book.png";
            title.setText(GradeFactory.getGradeNameByBook(bookShelfItem.getName()));
            /*if (position < mBooks.size()){
                imageView.setBackgroundResource(mBooks.get(position).getBackgroundResourceId());
            }*/
            imageView.setImageBitmap(getLocalBitmap(mContext, bookImg));


            return convertView;
        }

        private Bitmap getLocalBitmap(Context context, String url) {
            try{
                InputStream is = context.getApplicationContext().getAssets().open(url);
                return BitmapFactory.decodeStream(is);
            }catch(Exception e){

            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return mBookShelfItems.size();
        }
    }
    // end BookShelfAdapter

    /**
     * 排序 book5, book6, book7
     */
    private class SortBookName implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            String book1 = (String)lhs;
            String book2 = (String)rhs;
            int id1 = Integer.parseInt(book1.substring(4, book1.length()));
            int id2 = Integer.parseInt(book2.substring(4, book2.length()));
            if (id1 > id2){
                return 1;
            }else{
                return -1;
            }
        }
    }

}
