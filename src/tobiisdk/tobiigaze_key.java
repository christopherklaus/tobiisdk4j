package tobiisdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct hold a key for unlocking an eye tracker. <br>
 * @field data The key.<br>
 * <i>native declaration : tobii_gaze_sdk\include\tobiigaze_data_types.h:282</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiSDK") 
public class tobiigaze_key extends StructObject {
	/// C type : uint8_t[TOBIIGAZE_KEY_SIZE]
	@Array({(int)tobiisdk.TobiiSDKLibrary.tobiigaze_constants.TOBIIGAZE_KEY_SIZE.value()}) 
	@Field(0) 
	public Pointer<Byte > data() {
		return this.io.getPointerField(this, 0);
	}
	public tobiigaze_key() {
		super();
	}
	public tobiigaze_key(Pointer pointer) {
		super(pointer);
	}
}
