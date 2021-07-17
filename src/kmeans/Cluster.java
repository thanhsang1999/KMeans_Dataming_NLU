package kmeans;

public class Cluster {
	private int ageCentroid;
	private int incomeCentroid;
	private int scoreCentroid;
	private int clusterNumber;
	public Cluster(int clusterNumber, int ageCentroid, int incomeCentroid, int scoreCentroid) {
		super();
		this.clusterNumber = clusterNumber;
		this.ageCentroid = ageCentroid;
		this.incomeCentroid = incomeCentroid;
		this.scoreCentroid = scoreCentroid;
	}
	public int getAgeCentroid() {
		return ageCentroid;
	}
	public void setAgeCentroid(int ageCentroid) {
		this.ageCentroid = ageCentroid;
	}
	public int getIncomeCentroid() {
		return incomeCentroid;
	}
	public void setIncomeCentroid(int incomeCentroid) {
		this.incomeCentroid = incomeCentroid;
	}
	public int getScoreCentroid() {
		return scoreCentroid;
	}
	public void setScoreCentroid(int scoreCentroid) {
		this.scoreCentroid = scoreCentroid;
	}
	public int getClusterNumber() {
		return clusterNumber;
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}
	
	@Override
	public String toString() {
		return "Cluster [Số cụm=" + clusterNumber + ", ageCentroid=" + ageCentroid + ", incomeCentroid="
				+ incomeCentroid + ", scoreCentroid=" + scoreCentroid + "]";
	}
	/** Tình khoảng cách trung bình của tâm và bản ghi Math.sqrt(Math.pow(x-x'),2)+Math.pow(y-y'),2))
	 * @param record bản ghi thêm vào cụm
	 *  */
	public double calculateDistance(Record record) {
		return Math.sqrt(Math.pow(getAgeCentroid()-record.getAge(),2)+Math.pow(getIncomeCentroid()-record.getIncome(),2)+Math.pow(getScoreCentroid()-record.getScore(),2));
	}

	/** Cập nhật tâm của cụm (trung bình cộng mỗi thuộc tính bản ghi với tâm)
	 * @param record bản ghi thêm vào cụm
	 *  */
	public void updateCentroid(Record record) {
		setAgeCentroid((getAgeCentroid()+record.getAge())/2);
		setIncomeCentroid((getIncomeCentroid()+record.getIncome())/2);
		setScoreCentroid((getScoreCentroid()+record.getScore())/2);
	}
}
