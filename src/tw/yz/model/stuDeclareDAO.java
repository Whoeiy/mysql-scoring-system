package tw.yz.model;

import java.util.List;
import java.util.Map;

import tw.yz.domain.Extra;
import tw.yz.domain.ExtraName;


public interface stuDeclareDAO{
	/**
	 * 1.1 申请加分项: 获得类别etr对应的加分项明细
	 * @param etr
	 * @return
	 * @author Yu Yue
	 */
	public Map<String,ExtraName> queryDetails(String etr);
	
	/**
	 * 1.2 申请加分项: 插入一项加分项记录
	 * @param etr
	 * @return
	 * @author Yu Yue
	 */
	public int insertExtra(Extra etr);
	
	public Map<Integer,Extra> queryExtrasBy(String sno, String etr);
	
}
