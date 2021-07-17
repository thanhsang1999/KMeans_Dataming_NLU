package kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KMeans {

	List<Record> data = new ArrayList<Record>();
	List<Cluster> clusters = new ArrayList<Cluster>();
	Map<Cluster, List<Record>> clusterRecords = new HashMap<Cluster, List<Record>>();
	
	public static void main(String[] args) {
		
		int clusterNumber = 2;
		KMeans demo = new KMeans();
		
		demo.genereateRecord();

		demo.initiateClusterAndCentroid(clusterNumber);
		
		demo.printRecordInformation();
		demo.printClusterInformation();
	}

	/** Khởi tạo dữ liệu
	 * @clusterNumber => n :
	 * n Dữ liệu đầu sẽ là n tâm của cụm nên chọn n dòng đầu sao cho tiêu điểm nhất
	 * mỗi cụm
	 *  */
	private void genereateRecord() {
		Record record = new Record(1, 19, 15, 39);
		data.add(record);
		record = new Record(2, 21, 15, 81);
		data.add(record);
		record = new Record(3, 20, 16, 6);
		data.add(record);
		record = new Record(4, 23, 16, 77);
		data.add(record);
		record = new Record(5, 31, 17, 40);
		data.add(record);
		record = new Record(6, 22, 17, 76);
		data.add(record);
		
	}

	/**Khởi tạo cụm và tâm (Thuật toán KMeans xử lý chính ở đây)
	 * @param clusterNumber là số cụm muốn tạo
	 */
	private void initiateClusterAndCentroid(int clusterNumber) {
		//giá trị mặc định của bộ đếm là 1
		int counter = 1;
		//đưa data đang dạng dữ liệu array list về iterator (dạng duyệt tuần tự)
		Iterator<Record> iterator = data.iterator();
		// Khởi  tạo Class Record giá trị mặc định null => để duyệt
		Record record = null;
		
		while(iterator.hasNext()) {
			record = iterator.next();
			//Chỗ này sẽ khởi tạo n cụm cùng tâm là bản ghi(record)
			if(counter <= clusterNumber) {
				record.setClusterNumber(counter);
				initializeCluster(counter, record);
				counter++;
			}else {
				System.out.println(record);
//				System.out.println("** Thông tin cụm **");
//				for(Cluster cluster : clusters) {
//					System.out.println(cluster);
//				}
//				System.out.println("*********************");
                double minDistance = Integer.MAX_VALUE;
                Cluster whichCluster = null;
				/* 
				 * Duyệt từng cụng và chọn bản ghi có khoảng cách nhỏ nhất để thêm vào danh sách cụm
				 * sau khi thêm vào cụm thành công thì cập nhật tâm của cụm
				 * */
                for(Cluster cluster : clusters) {
                	double distance = cluster.calculateDistance(record);
                	System.out.println("Khoảng cách với tâm cụm "+cluster.getClusterNumber()+": "+distance);
                	if(minDistance > distance) {
                		minDistance = distance;
                		whichCluster = cluster;
                	}
                }
                
                record.setClusterNumber(whichCluster.getClusterNumber());
				whichCluster.updateCentroid(record);
				clusterRecords.get(whichCluster).add(record);

			}
			//In thông tin cụm mỗi lần lặp
			System.out.println("** Thông tin cụm **");
			for(Cluster cluster : clusters) {
				System.out.println(cluster);
			}
			System.out.println("*********************");

			
		}

	}

	/** Khởi tạo cụm
	 * @param clusterNumber số của cụm
	 * @param record Bạn ghi đầu tiên vào cụm (Tâm ban đầu)
	 *  */
	private void initializeCluster(int clusterNumber, Record record) {
		
		Cluster cluster = new Cluster(clusterNumber,record.getAge(),record.getIncome(),record.getScore());
		clusters.add(cluster);
		List<Record> clusterRecord = new ArrayList<Record>();
		clusterRecord.add(record);
		clusterRecords.put(cluster, clusterRecord);

	}

	/** In thông tin toàn bộ bản ghi
	 * */
	private void printRecordInformation() {
		   System.out.println("****** Thông tin toàn bộ bản ghi *********");
		   for(Record record : data) {
			   System.out.println(record);
		   }
	   }
	/** In thông tin toàn bộ cụm gồm tâm,là các bản ghi của cụm
	 * */
	private void printClusterInformation() {
		System.out.println("");
	   System.out.println("****** Hoàn thành: Toàn bộ dữ liệu về cụm *********");
	   System.out.println("");
	   for (Map.Entry<Cluster, List<Record>> entry : clusterRecords.entrySet())  {
        System.out.println("Tên cụm = " + entry.getKey() + 
                         ",\nBản ghi của cụm = " + entry.getValue());
    }
}


}
