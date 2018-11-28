package org.zyyd.base.util;

public class PageUtil {
	private long RecordCount = 0; // 记录总数

	private long CurrentPageNum = 1; // 当前页码

	private long PageCount = 0; // 总页数

	private long CountPerPage = 10; // 每页记录数

	private long FirstPageNum = 0; // 显示的第一个页码

	private long LastPageNum = 0; // 显示的最后一个页码

	private long PNCountPerPage = 10;// 每页显示的页码数

	private long FirstRecordNum = 0; // 当前页第一个记录

	private long LastRecordNum = 0; // 当前页最后一个记录
	
	
	public PageUtil(long _RecordCount, long _CountPerPage, long _PNCountPerPage) {
		// 初始化总记录数
		RecordCount = _RecordCount;

		// 初始化每页记录数
		CountPerPage = _CountPerPage;

		// 初始化总页数
		if (_RecordCount % _CountPerPage != 0) {
			PageCount = _RecordCount / _CountPerPage + 1;
		} else {
			PageCount = _RecordCount / _CountPerPage;
		}

		// 初始化每页显示页码数
		PNCountPerPage = _PNCountPerPage;
	}

	public void goToPage(long _PageNum) {
		if (_PageNum <= PageCount) {

			if (PageCount <= PNCountPerPage) {// 总页数小于等于每页显示页码数
				// 修改显示的第一个页码
				this.FirstPageNum = 1;
				// 修改显示的最后一个页码
				this.LastPageNum = PageCount;
				// 修改当前页第一个记录
				this.FirstRecordNum = (_PageNum - 1) * this.CountPerPage;
				// 修改当前页最后一个记录
				if (_PageNum == this.PageCount) {// 最后一页
					this.LastRecordNum = this.RecordCount - 1;
				} else {// 不是最后一页
					this.LastRecordNum = _PageNum * this.CountPerPage - 1;
				}
				// 修改当前页码
				this.CurrentPageNum = _PageNum;

			} else {// 总页数大于每页显示页码数
				// 修改显示的第一个页码

				long fc = _PageNum - (this.PNCountPerPage / 2);

				if (fc < 1)
					fc = 1;
				this.setFirstPageNum(fc);

				// 修改显示的最后一个页码
				long lc = _PageNum + (this.PNCountPerPage / 2);
				if (lc > this.PageCount)
					lc = this.PageCount;
				this.setLastPageNum(lc);

				// 修改当前页第一个记录
				this.FirstRecordNum = (_PageNum - 1) * this.CountPerPage;
				// 修改当前页最后一个记录
				if (_PageNum == this.PageCount) {// 最后一页
					this.LastRecordNum = this.RecordCount - 1;
				} else {// 不是最后一页
					this.LastRecordNum = _PageNum * this.CountPerPage - 1;
				}
				// 修改当前页码
				this.CurrentPageNum = _PageNum;
			}

		} else {
		}
	}

	public long getCountPerPage() {
		return CountPerPage;
	}

	public void setCountPerPage(long countPerPage) {
		CountPerPage = countPerPage;
	}

	public long getCurrentPageNum() {
		return CurrentPageNum;
	}

	public void setCurrentPageNum(long currentPageNum) {
		CurrentPageNum = currentPageNum;
	}

	public long getFirstPageNum() {
		return FirstPageNum;
	}

	public void setFirstPageNum(long firstPageNum) {
		FirstPageNum = firstPageNum;
	}

	public long getFirstRecordNum() {
		return FirstRecordNum;
	}

	public void setFirstRecordNum(long firstRecordNum) {
		FirstRecordNum = firstRecordNum;
	}

	public long getLastPageNum() {
		return LastPageNum;
	}

	public void setLastPageNum(long lastPageNum) {
		LastPageNum = lastPageNum;
	}

	public long getLastRecordNum() {
		return LastRecordNum;
	}

	public void setLastRecordNum(long lastRecordNum) {
		LastRecordNum = lastRecordNum;
	}

	public long getPageCount() {
		return PageCount;
	}

	public void setPageCount(long pageCount) {
		PageCount = pageCount;
	}

	public long getPNCountPerPage() {
		return PNCountPerPage;
	}

	public void setPNCountPerPage(long countPerPage) {
		PNCountPerPage = countPerPage;
	}

	public long getRecordCount() {
		return RecordCount;
	}

	public void setRecordCount(long recordCount) {
		RecordCount = recordCount;
	}

	public String getPageCode1() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page2\">";
		pagecode=pagecode+"<p>共"+this.getRecordCount()+"条</p>";
		pagecode=pagecode+"<ul>";
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<li><a  class=\"pre\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">&lt;上一页</a>";
		}else{
			pagecode=pagecode+"<li><a  class=\"pre\" href='###' >&lt;上一页</a></li>";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<li><a href=\"javascript:goToPage(1)\">1</a>...</li>";
    	}
	    for(long i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<li><span class=\"cur\">"+i+"</span></li> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<li><a class=\"num\" href=\"javascript:goToPage("+i+")\">"+i+"</a></li>";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"<li>...<a class=\"num\" href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a></li>";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<li><a class=\"next\" href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\">下一页 &gt;</a></li>";
		}else{
			pagecode=pagecode+"<li><a href=\"###\" class=\"next\" >下一页 &gt;</a></li>";
		}
	    pagecode=pagecode+"</ul></div>";
		return pagecode;
	}
	public String getPageCode() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"sui-pagination pagination-large\">";
		pagecode=pagecode+"<ul>";
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<li class=\"prev\"><a href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">上一页</a></li> ";
		}else{
			pagecode=pagecode+"<li class=\"prev disabled\"><a href=\"#\">上一页</a></li> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<li><a href=\"javascript:goToPage(1)\">1</a><li> ";
			pagecode=pagecode+"<li class=\"dotted\"><span>...</span></li> ";
    	}
	    for(long i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<li class=\"active\"><a href=\"#\">"+i+"</a></li> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<li><a href=\"javascript:goToPage("+i+")\">"+i+"</a></li> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
	    	pagecode=pagecode+"<li class=\"dotted\"><span>...</span></li> ";
			pagecode=pagecode+"<li><a href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a></li> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<li class=\"next\"><a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\">下一页</a></li> ";
		}else{
			pagecode=pagecode+"<li class=\"next disabled\"><a href=\"#\">下一页</a></li> ";
		}
	    pagecode=pagecode+"</ul>";
	    pagecode=pagecode+"<div><span> 共"+this.getPageCount()+"页&nbsp;</span><span>";
	    pagecode=pagecode+"到 <input type=\"text\" name=\"queryPageNo\" id=\"queryPageNo\" class=\"page-num\"><button class=\"page-confirm\" onclick=\"goToPage($('#queryPageNo').val())\">确定</button> 页</span></div>";
	    pagecode=pagecode+"</div>";
		return pagecode;
	}
	/**
	 * 
     *类的描述:<br>
     *功能描述 ：页码显示<br>
     *作者:hyl<br>
     *创建日期:2015-08-07<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述：增加记录的总条数<br>
	 */
	public String getPageCode2() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page\">";
		pagecode=pagecode+"<span style=\"margin-top:10px;font-size:12px;margin-right:5px;color:#06a284;\">共 "+this.getRecordCount()+" 条</span>";
		if(this.getCurrentPageNum()!=1)
		{
			//pagecode=pagecode+"<a class=\"pre\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">pre</a> ";
		}else{
			//pagecode=pagecode+"<a href=\"###\" class=\"pre\">pre</a> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<a class=\"num\" href=\"javascript:goToPage(1)\">1</a> ... ";
    	}
	    for(long i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<span class=\"num\">"+i+"</span> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<a class=\"num\" href=\"javascript:goToPage("+i+")\">"+i+"</a> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"...<a  class=\"num\" href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			//pagecode=pagecode+"<a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\" class=\"next\">next</a> ";
		}else{
			//pagecode=pagecode+"<a  href=\"###\" class=\"next\">next</a> ";
		}
	    pagecode=pagecode+"<input type=\"text\" class=\"input\" name=\"queryPageNo\" id=\"queryPageNo\" placeholder=\"输入页数\" /> <a href=\"javascript:goToPage($('#queryPageNo').val())\" class=\"to\">跳转</a>";
	    pagecode=pagecode+"</div>";
		return pagecode;
	}
	
	/**
	 * gdw
	 * 用户中心,附属机构分页(共 349645 条 左边   1 2 3 4 ... 2355   共 25 页 右边  跳转到 页)
	 */
	public String getPageCodeAll() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page\"><div class=\"inner\">";
		pagecode=pagecode+"<span >共 <i>"+this.getRecordCount()+"</i>条</span>";
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<a class=\"prev\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">&nbsp;</a> ";
		}else{
			pagecode=pagecode+"<a href=\"###\" class=\"prev\">&nbsp;</a> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<a href=\"javascript:goToPage(1)\">1</a>... ";
    	}
	    for(long i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<b class=\"on\">"+i+"</b> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<a href=\"javascript:goToPage("+i+")\">"+i+"</a> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"...<a href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\" class=\"next\"></a> ";
		}else{
			pagecode=pagecode+"<a  href=\"###\" class=\"next\"></a> ";
		}
	    pagecode=pagecode+"<span>共 <i>"+this.PageCount+"</i> 页，跳转到<input type=\"text\" name=\"queryPageNo\" id=\"queryPageNo\"/> <a href=\"javascript:goToPage($('#queryPageNo').val())\" class=\"to\">go</a></span>";
	    pagecode=pagecode+"</div></div>";
		return pagecode;
	}
	/**
	 * gdw
	 * @return
	 */
	public Object getPageCodeOfTongyong() {
		if(this.RecordCount==0) return "";
		String pagecode="";
		pagecode=pagecode+"<div class=\"page\"><div class=\"inner\">";
		pagecode=pagecode+"<span >共 <i>"+this.getRecordCount()+"</i>条</span>";
		if(this.getCurrentPageNum()!=1)
		{
			pagecode=pagecode+"<a class=\"prev\" href=\"javascript:goToPage("+(this.CurrentPageNum-1)+")\">&nbsp;</a> ";
		}else{
			pagecode=pagecode+"<a href=\"###\" class=\"prev\">&nbsp;</a> ";
		}
    	if(this.getFirstPageNum()>1){
			pagecode=pagecode+"<a href=\"javascript:goToPage(1)\">1</a>... ";
    	}
	    for(long i=this.getFirstPageNum();i<=this.getLastPageNum();i++)
	    {
	    	if(this.getCurrentPageNum()==i)
	    	{
	    	pagecode=pagecode+"<b class=\"on\">"+i+"</b> ";	
	    	}
	    	else
	    	{
	    	pagecode=pagecode+"<a href=\"javascript:goToPage("+i+")\">"+i+"</a> ";	
	    	}
	    }
	    
	    if(this.getLastPageNum()<this.PageCount){
			pagecode=pagecode+"...<a href=\"javascript:goToPage("+this.PageCount+")\">"+this.PageCount+"</a> ";
    	}
	    
	    if(this.getCurrentPageNum()!=this.getLastPageNum())
		{
			pagecode=pagecode+"<a href=\"javascript:goToPage("+(this.CurrentPageNum+1)+")\" class=\"next\"></a> ";
		}else{
			pagecode=pagecode+"<a  href=\"###\" class=\"next\"></a> ";
		}
	    pagecode=pagecode+"<span>共 <i>"+this.PageCount+"</i> 页，跳转到<input type=\"text\" name=\"queryPageNo\" id=\"queryPageNo\"/> <a href=\"javascript:goToPage($('#queryPageNo').val())\" class=\"to\">go</a></span>";
	    pagecode=pagecode+"</div></div>";
		return pagecode;
	}
}

