package kadai3;

import java.util.ResourceBundle;

public interface Fortune {

	ResourceBundle rb = ResourceBundle.getBundle("fortune");

//        System.out.println(rb.getString("disp_str"));

	String DISP_STR = rb.getString("disp_str");

	String disp();

}
