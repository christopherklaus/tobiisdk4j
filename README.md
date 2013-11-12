

# Tobii Gaze SDK (Java Bindings) #

Simple bindings for the latest [Tobii Gaze SDK](http://www.tobii.com/gaze-interaction/global/products-services/tobii-gaze-sdk/) to Java.

  * All-in-one package
  * Works with (and requires) 64-bit JVMs
  * Tested on Windows 8 



## Quickstart ##

Download the __[latest release of the Java Bindings](http://s.xr.io/tobiisdk4j/latest.zip)__.
  
Paste this code into your `main()`:

	final Configuration config = new Configuration().initialize();	
	final EyeTracker tracker = new EyeTracker(config);	

	final GazeListener listener = new GazeListener() {	

		public void gazeEvent(GazeEvent event) {
			System.out.println(event.center().eyePosInBoxNorm); 
		}
		
		public void apiException(APIException exception) {
			System.out.println(exception);
		}
	};
	
	tracker.connect().register(listener).start();	

In addition, you might want to add a `Thread.sleep(100000)`, to prevent the application from terminating.



## News / Changelog ##

  * (2013/11/12) -- Version 1.0, should work with all Tobii REX and X2 eye trackers. 

  * (2013/04/15) -- Initial release, core features working.



## FAQ ##

  * __Where can I find in-depth documentation?__

  Please see the [Tobii Gaze SDK](http://www.tobii.com/gaze-interaction/global/products-services/tobii-gaze-sdk/) for information on function calls and variable meanings.

  * __How much latency does the wrapping add?__

  The overall added latency should be well below 1ms. However, garbage collection in the JVM  might cause longer delays if an inappropriate collector is being used. In case you are developing latency-sensitive applications consult the [JVM documentation](http://www.oracle.com/technetwork/java/javase/tech/g1-intro-jsp-135488.html). 


  * __Does it work with 32bit JVMs?__

  No. Please update to a 64bit JVM.


## License ##

Read this carefully:

The actual __JAR library__ and its sources are licensed as [LGPL 2.1](http://www.gnu.org/licenses/lgpl-2.1.html). 

The underlying Tobii Gaze SDK __.dlls__ are licensed according to the [Tobii Gaze SDK Agreement](http://www.tobii.com/gaze-interaction/global/products-services/tobii-gaze-sdk/). In particular it does _not allow for developing applications that use eye tracking data for recording behavior_.



## Credits ##

  * Wrapper created by [Ralf Biedert](http://xr.io)

Uses code from [Tobii Technology AB](http://tobii.com) and [Bridj](https://code.google.com/p/bridj/).

