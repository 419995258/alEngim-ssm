package org.zyyd.base.util;



import java.util.List;

import org.zyyd.base.entity.vo.ResultVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class BasicService {
	/**
     * 设置分页初始信息
     * @param pagesize
     * @param currentpage
     */
	public void setPageInfo(Integer pageNo, Integer pageSize) {
		// TODO 设置分页初始信息
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNo, pageSize);

	}

	public void setPageInfo(Integer pageNo) {
		// TODO 设置分页初始信息
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, 10);

	}
	
	 /**
     * 设置返回的分页信息
     * @param pagesize
     * @param currentpage
     * @param pos
     * @param resultVo
     */
	public void setReturnPageInfo(Integer pagesize, Integer currentpage,
			List pos, ResultVo resultVo) throws Exception {
		// TODO 设置返回的分页信息
		
		if (pagesize != null || currentpage != null) {
			// 分页信息设置
			Page p = (Page) pos;

			resultVo.setCurrentPage(p.getPageNum());
			resultVo.setTotal(p.getTotal());

			resultVo.setPageNo(p.getPageNum());

			resultVo.setPageSize(p.getPageSize());

			int pages = p.getPages();
			resultVo.setPages(pages);

            resultVo.setRows(pos);
		}
		
	}

	public void setReturnPageInfo(Integer currentpage,
								  List pos, ResultVo resultVo) throws Exception {
		// TODO 设置返回的分页信息

		if (currentpage != null) {
			// 分页信息设置
			Page p = (Page) pos;

			resultVo.setCurrentPage(p.getPageNum());
			resultVo.setTotal(p.getTotal());

			resultVo.setPageNo(p.getPageNum());

			resultVo.setPageSize(p.getPageSize());

			int pages = p.getPages();
            resultVo.setPages(pages);

            resultVo.setRows(pos);
        }

	}
}
