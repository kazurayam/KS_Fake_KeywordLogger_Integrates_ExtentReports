# Design idea

The intenface `com.kazurayam.ks.reporting.ReportBuilder` declares method signatures you can implement in your own class. Your class that implements that inteface can be injected into the KeywordLogger object.

You custom class must be a singleton. It must implement the "getInstance()" method, the method must be static, must be public. The method should return the singleton instance of that class.

The "Bill Pugh Singleton" pattern is recommended. See https://www.baeldung.com/java-bill-pugh-singleton-implementation

The singleton ReportBuilder-implementing class will be instanciated only once when its "getInstance()" method is called by any portion.

The "CustomReportsListener" gives you chances to transfer events of start/end of test scripts into the ReportBuilders.
