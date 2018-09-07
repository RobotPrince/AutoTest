package TestOnePack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.yss.common.DBConnect;


public class DBInsertTest {

	public static void main(String[] args) throws SQLException, ParseException {
		Connection con = DBConnect.getConnection();
		String allJson = new String();
		Map<String, Object> JsonMap = new LinkedHashMap<String,Object>();
		 Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-04");
		String[] arr = {"18090314165573418959","ffffff","2","1","ffffff","670b14728ad9902aecba32e22fa4f6bd","1","0","0","0","0","1","date","date","12345678901234567890","date","12345678901234567890","date","12345678901234567890","date","0","1","12345678901234567890","date","date","0","ffffff","0"};
		 PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO t_User (FID,fname,fgender,ftype,flogin_code,fpwd,faccount_type,forigin_type,flogin_account_type,flock_state,forig_state,fstart_date,fend_date,fcreator_id,fcreate_time,flast_edit_time,fdelete_time,fdelete_state,fcheck_state,fchecker_id,fcheck_time"
		 		+ ",fpwd_eff_date,faccount_state,fnamepy,fspecial_user_state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 for(int j=1;j>0;j++){
			 
			for(int i =0;i<25;i++){
				arr[i]+=j;
				prepareStatement.setString(i+1, arr[i]);
			}
			prepareStatement.execute();
		 }
//		ResultSet resultSet = con.prepareStatement("select * from t_User").executeQuery();
//	System.out.println();
				}
}
