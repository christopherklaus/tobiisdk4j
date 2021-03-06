package tobii.lowlevel.sdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct holds configuration information.<br>
 * <i>native declaration : tobii_gaze_sdk/include/tobiigaze_data_types.h:555</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_config_key_value extends StructObject {
	/// C type : char[TOBIIGAZE_MAX_CONFIG_KEY_LENGTH]
	@Array({128}) 
	@Field(0) 
	public Pointer<Byte > key() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : char[TOBIIGAZE_MAX_CONFIG_KEY_LENGTH]
	@Array({128}) 
	@Field(1) 
	public Pointer<Byte > value() {
		return this.io.getPointerField(this, 1);
	}
	public tobiigaze_config_key_value() {
		super();
	}
	public tobiigaze_config_key_value(Pointer pointer) {
		super(pointer);
	}
}
