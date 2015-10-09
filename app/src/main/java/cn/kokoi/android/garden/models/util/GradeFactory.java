package cn.kokoi.android.garden.models.util;

/**
 * Created by zkl on 2015/5/13.
 */
public class GradeFactory {

    /**
     * 返回指定册的目录名
     * @param bookId
     * @return
     */
    public static String getBookDirNameById(int bookId){
        return "book" + bookId;
    }
    
    public static String getGradeNameByBookId(int bookId){
        return getGradeNameByBook(getBookDirNameById(bookId));
    }
    
    /**
     * 根据册数返回年级
     * 如
     * book1 -> 一年级上册
     * book2 -> 一年级下册
     * ...
     * @param book
     * @return
     */
    public static String getGradeNameByBook(String book){
        String ret= "一年级上册";
        switch (book.toLowerCase()){
            case "book1":
                ret = "一年级上册";
                break;
            case "book2":
                ret = "一年级下册";
                break;
            case "book3":
                ret = "二年级上册";
                break;
            case "book4":
                ret = "二年级下册";
                break;
            case "book5":
                ret = "三年级上册";
                break;
            case "book6":
                ret = "三年级下册";
                break;
            case "book7":
                ret = "四年级上册";
                break;
            case "book8":
                ret = "四年级下册";
                break;
            case "book9":
                ret = "五年级上册";
                break;
            case "book10":
                ret = "五年级下册";
                break;
            case "book11":
                ret = "六年级上册";
                break;
            case "book12":
                ret = "六年级下册";
                break;
        }
        return ret;
    }
}
