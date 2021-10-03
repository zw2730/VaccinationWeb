package DB.bean;

public class Group {
	private int gid;
	private String qualify;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getQualify() {
		return qualify;
	}
	public void setQualify(String qualify) {
		this.qualify = qualify;
	}
	@Override
	public String toString() {
		return "Group [gid=" + gid + ", qualify=" + qualify + "]";
	}
	
}
