package cn.kokoi.android.garden.interfaces;

import java.io.InputStream;
import java.util.List;

import cn.kokoi.android.garden.models.reading.Unit;

/**
 * Created by zkl on 2015/4/22.
 */
public interface IConfigParser {

    /**
     * 解析输入流，得到 Unit对象集合
     * @param is
     * @return
     * @throws Exception
     */
    public List<Unit> parse( InputStream is) throws Exception;

    /**
     * 序列化 Unit集合对象，得到XML形式的字符串
     * @param units
     * @return
     * @throws Exception
     */
    public String serialize(List<Unit> units) throws Exception;
}
