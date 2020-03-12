package presentation;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import model.*;
import java.lang.reflect.*;

public class Tabel {
	

	public <T> DefaultTableModel CreateModel(ArrayList<T> arrayList) {
		ArrayList<String> columns = new ArrayList<String>();

		if (arrayList.size() > 0) {
			for (Field field : arrayList.get(0).getClass().getDeclaredFields())
				if (!field.getName().equals("isDeleted"))
					columns.add(field.getName());
		}
		DefaultTableModel model = new DefaultTableModel(columns.toArray(), 0);

		for (Object obj : arrayList) {
			int deleted = 0;

			Vector<String> values = new Vector<String>();
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				try {
					String val = field.get(obj).toString();
					if (field.getName().equals("isDeleted")) {
						if (field.get(obj).toString().equals("true"))
							deleted = 1;
					} else
						values.add(val);
				} catch (IllegalArgumentException|IllegalAccessException e) {
					e.printStackTrace();
				} 
			}
			if (deleted == 0)
				model.addRow(values);
		}

		return model;

	}
}