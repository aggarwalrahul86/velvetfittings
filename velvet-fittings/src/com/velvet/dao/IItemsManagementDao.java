package com.velvet.dao;

import java.util.List;
import java.util.Map;

import com.velvet.vo.ItemOrderBean;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.ManageItemOrderBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.OrderBean;
import com.velvet.vo.OrderSearchBean;
import com.velvet.vo.OrderSearchResultBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;

public interface IItemsManagementDao {
	
	
	String ITEM_NAME_PATTERN = "ITEM_NAME_PATTERN";
	String CLIENT_NAME_PATTERN = "CLIENT_NAME_PATTERN";
	
	public static final String GET_CATEGORY_QUERY = new StringBuilder("SELECT category_sl_no as 'code',category_code as 'desc',category_name as 'name' FROM tbl_item_category_master where logical_delete='N' order by upper(category_name) ").toString();
	
	public static final String GET_ITEMS_QUERY = new StringBuilder(" SELECT tim.item_sl_no as 'code',tim.item_code as 'desc', concat(tim.item_name,'_(',ticm.category_name,')') as 'name' ")
	.append(" FROM tbl_item_master tim, tbl_item_category_master ticm ")
	.append(" where tim.category_sl_no = ticm.category_sl_no ")
	.append(" and IF(:category IS NULL, TRUE , tim.category_sl_no = :category) ")
	.append(" and tim.item_name like '"+ITEM_NAME_PATTERN+"%' ")
	.append(" and tim.logical_delete='N' ")
	.append(" order by upper(tim.item_name) ")
	.toString();
	
	public static final String GET_CLIENTS_QUERY = new StringBuilder(" select tordr.client_name as 'code', tordr.client_name as 'name', tordr.client_name as 'desc' ")
	.append(" from tbl_order tordr ")
	.append(" where tordr.logical_delete='N' ")
	.append(" and tordr.client_name like '"+CLIENT_NAME_PATTERN+"%' ")
	.append(" group by tordr.client_name ")
	.append(" order by upper(tordr.client_name) ")
	.toString();
	
	public static final String INSERT_ITEM_TRANSACTION_QUERY = new StringBuilder("INSERT INTO tbl_item_transaction(item_sl_no, order_sl_no, item_quantity, created_by, created_on, modified_by, modified_on, logical_delete) VALUES(:item_sl_no, :order_sl_no, :item_quantity,  'admin', now(), 'admin', now(), 'N');").toString();
	
	public static final String SQL_UPDATE_PARTS_QUANTITY  = new StringBuilder("update tbl_part_master set part_quantity  = :partQnty where part_sl_no = :partId").toString();
	
	public static final String DELETE_ITEM_TRANSACTION_FOR_ORDER = new StringBuilder("delete from tbl_item_transaction where order_sl_no = :orderSlNo ").toString();
	
	public List<NameValuePairBean> getCategoryList() throws Exception;
	
	public List<NameValuePairBean> getItemList(String category, String itemNamePattern) throws Exception;
	
	public Map<String, Parts> getPartsInfoMapForItems(String itemIdCSV) throws Exception;
	
	public Map<String, Map<String, ItemPartMappingBean>> getItemPartMappingInfoMapForItems(String itemIdCSV) throws Exception;
	
	public OrderBean placeOrderForItems(ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception;
	
	public List<OrderSearchResultBean> getOrderSearchResults(OrderSearchBean orderSearchBean) throws Exception;
	
	public List<ItemOrderBean> getItemDetailsForOrderId(String orderId) throws Exception;
	
	public ManageItemOrderBean getOrderDetailsForOrderId(String orderId, ManageItemOrderBean manageItemOrderBean) throws Exception;
	
	public List<ItemOrderBean> getItemOrderDetailsForOrderId(String orderId) throws Exception;
	
	public OrderBean updateOrderForItems(String orderId, ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, 
			Map<String, PartAvailabilityBean> partAvailabilityMap, Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap) throws Exception;
	
	public List<NameValuePairBean> getClientList(String clientNamePattern) throws Exception;
}
