package proyectoDB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.csvreader.CsvWriter;

public class CSVExporter {

    private Connection c;

    public CSVExporter(Connection c) {
        this.c = c;
    }

    private static final String LOTE_QUERY="SELECT * FROM Lote",
                                PROD_QUERY="SELECT * FROM Producto",
                                ENV_QUERY="SELECT * FROM Envase",
                                ENV_PROD_QUERY="SELECT * FROM ProductoEnvasado";

    private static final List<String> QUERY_LIST= Collections.unmodifiableList(Arrays.asList(LOTE_QUERY,PROD_QUERY,ENV_PROD_QUERY,ENV_QUERY));

    private static final Map<String,String> QUERY_TO_FILENAME=new HashMap<>();

    static{
        QUERY_TO_FILENAME.put(LOTE_QUERY,"lote.csv");
        QUERY_TO_FILENAME.put(PROD_QUERY,"producto.csv");
        QUERY_TO_FILENAME.put(ENV_QUERY,"envase.csv");
        QUERY_TO_FILENAME.put(ENV_PROD_QUERY,"productoEnvasado.csv");
    }


    private boolean query(String query,File directory){
        boolean status=true;
        try {

            PreparedStatement statement = this.c.prepareStatement(query);

            ResultSet rs=statement.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount=rsmd.getColumnCount();

            List<String> columns=new ArrayList<>();

            for(int i=1;i<=columnCount;i++){
                columns.add(rsmd.getColumnName(i));
            }

            System.out.println(columns);

            File outputFile=new File(directory.getAbsolutePath()+"/"+QUERY_TO_FILENAME.get(query));

            if(outputFile.exists()){
                outputFile.delete();
            }

            CsvWriter output=new CsvWriter(new FileWriter(outputFile),',');


            for(String column : columns){
                output.write(column);
            }

            output.endRecord();

            List<String> records;

            while(rs.next()){
                records=new ArrayList<>();

                for(String column : columns){
                    records.add(rs.getString(column));
                }

                for(String record : records){
                    output.write(record);
                }

                output.endRecord();

            }

            output.close();

        } catch (SQLException | IOException e) {
            status=false;
        }
        return status;
    }

    public boolean accept(File destDirectory){

        boolean resultStatus=true;

        for (String query : QUERY_LIST){
            resultStatus= resultStatus && this.query(query,destDirectory);
        }

        return resultStatus;
    }

}
