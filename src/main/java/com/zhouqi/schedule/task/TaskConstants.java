package com.zhouqi.schedule.task;

public class TaskConstants {

    public interface DateRelate
    {
        /**runtime you can used to determinite the extract data.
         * Must support run the history data.
         * */
        String DATE_RUN="DATE.RUN";
        String UTIL_NOW ="DATA.UTIL.LAST";
    }


    public interface FileRelate
    {
        String STORE_PATH="FILE.PATH.STORE";
        String ERR_PATH="FILE.PATH.ERROR";
        String BAK_PATH="FILE.PATH.BACKUP";
        String INVALID_PATH="FILE.PATH.INVALID";


        /**File format relate*/
        public static String FILE_ROW_FORMAT="FILE.FORMAT.ROW.FORMAT";
        public static String FILE_ROW_SEPARATOR="FILE.FORMAT.ROW.SEPARATOR";
        public static String FILE_COLUMN_SEPARATOR="FILE.FORMAT.COLUMN.SEPARATOR";
        public static String FILE_INCLUDE_HEADER ="FILE.FORMAT.INCLUDE_HEADER";
        /****/
        public static String FILE_HEADERS ="FILE.FORMAT.HEADERS";
        /**when include multiple header just separator by $$*/
        public static String FILE_LINES_SEPARATOR ="$$";
        /**when tail include.we just add the rows passed**/
        public static String FILE_INCLUDE_TAIL ="FILE.FORMAT.INCLUDE_TAIL";
        public static String FILE_IGNORED_ROWS = "FILE.FORMAT.ROWS.IGNORED";
        public static String FILE_ENCODING = "FILE.FORMAT.ENCODING";


        public static String CHECK_FILE_ROW_FORMAT="FILE.CHECK.FORMAT.ROW.FORMAT";
        public static String CHECK_FILE_ROW_SEPARATOR="FILE.CHECK.FORMAT.ROW.SEPARATOR";
        public static String CHECK_FILE_COLUMN_SEPARATOR="FILE.CHECK.FORMAT.COLUMN.SEPARATOR";
        public static String CHECK_FILE_ENCODING = "FILE.CHECK.FORMAT.ENCODING";
        /**1:CONTINUE,0:BREAK*/
        public static String FILE_INVALID_CONTINUE = "FILE.FORMAT.INVALID.CONTINUE";
        public static String FILE_COMPRESSION_IS = "FILE.FORMAT.COMPRESSION.IS";
        public static String FILE_COMPRESSION_SUFFIX = "FILE.FORMAT.COMPRESSION.SUFFIX";
        /**Check File Name*/
        public static String FILE_CHECK_SUFFIX = "FILE.FORMAT.CHECK.SUFFIX";
        public static String FILE_NEED_CHECK = "FILE.FORMAT.CHECK.NEED";



        /**File name convention**/
        public static String FILE_NAME_BIZCODE="FILE.NAME.BIZCODE";
        public static String FILE_NAME_PREFIX="FILE.NAME.PREFIX";
        public static String FILE_NAME_SUFFIX="FILE.NAME.SUFFIX";
        public static String FILE_NAME_SEQUENCE="FILE.NAME.SEQUENCE";
        public static String FILE_NAME_EXTENSION="FILE.NAME.EXTENSION";
        public static String FILE_NAME_DATA_DATE="FILE.NAME.DATE_DATA";
        public static String FILE_NAME_OPER_DATE="FILE.NAME.DATE_OPER";
        public static String FILE_NAME_DATA_DATE_FORMAT="FILE.NAME.DATE.DATA.FORMAT";
        public static String FILE_NAME_OPER_DATE_FORMAT="FILE.NAME.DATE.OPER.FORMAT";
        public static String FILE_NAME_FORMAT="FILE.NAME.FORMAT";
    }



    public interface FtpRelate
    {
        public static String FTP_HOST="FTP.HOST";
        public static String FTP_PORT="FTP.PORT";
        public static String FTP_USER="FTP.USERNAME";
        public static String FTP_PWD="FTP.PASSWORD";
        /**Interface level*/
        public static String FTP_DIR="FTP.DIR";
        /**1:NEDD,0:no need**/
        public static String FTP_NEED_UPLOAD="FTP.UPLOAD.IS";
        public static String FTP_SYS="FTP.SYS";
        public static String FTP_DEFAULT_WORKING_DIRECTORY="FTP.REMOTE.DEFAULT.DIR";
        public static String FTP_REMOTE_SRC_DIRECTORY="FTP.REMOTE.SRC.DIR";
        public static String FTP_LOCAL_SRC_DIRECTORY="FTP.LOCAL.SRC.DIR";
        public static String FTP_REMOTE_DEST_DIRECTORY="FTP.REMOTE.DEST.DIR";
        public static String FTP_LOCAL_DEST_DIRECTORY="FTP.LOCAL.DEST.DIR";
        public static String FTP_REMOTE_NAME_FILTER_PTTERN="FTP.REMOTE.FILTER.PATTERN";
        public static String FTP_LOCAL_NAME_FILTER_PTTERN="FTP.LOCAL.FILTER.PATTERN";
        public static String SFTP_ENABLE="FTP.SECURITY.ENABLE";
        public static String SFTP_PROTOCOL="FTP.SECURITY.PROTOCOL";
    }



    public interface DBRelate
    {
        public static String DB_QUERY_BATCH_SIZE="DB.QUERY.BATCH.SIZE";
    }


    public interface PropertiesName
    {
        public static String CFG_MODE="task.cfg.mode";
    }

}
