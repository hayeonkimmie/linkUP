package util;

public class PageInfo {
	private Integer cur_page;
	private Integer all_page;
	private Integer start_page;
	private Integer end_page;

	public PageInfo() {}

	public PageInfo(Integer cur_page) {
		this.cur_page = cur_page;
	}

	public Integer get_cur_page() {
		return cur_page;
	}

	public void set_cur_page(Integer cur_page) {
		this.cur_page = cur_page;
	}

	public Integer get_all_page() {
		return all_page;
	}

	public void set_all_page(Integer all_page) {
		this.all_page = all_page;
	}

	public Integer get_start_page() {
		return start_page;
	}

	public void set_start_page(Integer start_page) {
		this.start_page = start_page;
	}

	public Integer get_end_page() {
		return end_page;
	}

	public void set_end_page(Integer end_page) {
		this.end_page = end_page;
	}
}
