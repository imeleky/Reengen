package com.reengen.auditreporter.action.base;

/**
 * Created by melek on 13.02.2017.
 */

import com.reengen.auditreporter.conf.Constants;

/** Abstract class of ReportGenerator classes*/
abstract public class AbstractReportGenerator {

     /**Concrete method for report generation*/
     final public void generateReport(int topValue)
     {
         /** Controls if topValue is valid for Top N Reporting */
         if(Constants.TOP_REPORT_VALUE == topValue ) {
             createFullReport();
         }
         else {
             createTopSizedReport(topValue);
         }
     }

    protected abstract void createTopSizedReport(int topValue);

    protected abstract void createFullReport();
    
    


}
