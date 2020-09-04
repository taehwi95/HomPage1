package common;

public class ViewForward {
	
	private String path;	// 결과를 보여줄 VIEW 이름
	private boolean isRedirect;	// forward or redirect 할 것이냐 (기본값 : forward) 이유? boolean은 false가 기본값이기때문에
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}
