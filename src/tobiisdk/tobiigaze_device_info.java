package tobiisdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct holds Device Info that is fetched from the Eye Tracker. The char arrays holds null <br>
 * terminated strings.<br>
 * @field serial_number The serial number of the eye tracker.<br>
 * @field model The eye tracker model, e.g. "REX_DEV_Laptop".<br>
 * @field generation The eye tracker generation, e.g. G5.<br>
 * @field firmware_version The eye tracker serial number.<br>
 * <i>native declaration : tobii_gaze_sdk\include\tobiigaze_data_types.h:115</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiSDK") 
public class tobiigaze_device_info extends StructObject {
	/// C type : char[TOBIIGAZE_DEVICE_INFO_MAX_SERIAL_NUMBER_LENGTH]
	@Array({(int)tobiisdk.TobiiSDKLibrary.tobiigaze_constants.TOBIIGAZE_DEVICE_INFO_MAX_SERIAL_NUMBER_LENGTH.value()}) 
	@Field(0) 
	public Pointer<Byte > serial_number() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : char[TOBIIGAZE_DEVICE_INFO_MAX_MODEL_LENGTH]
	@Array({(int)tobiisdk.TobiiSDKLibrary.tobiigaze_constants.TOBIIGAZE_DEVICE_INFO_MAX_MODEL_LENGTH.value()}) 
	@Field(1) 
	public Pointer<Byte > model() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : char[TOBIIGAZE_DEVICE_INFO_MAX_GENERATION_LENGTH]
	@Array({(int)tobiisdk.TobiiSDKLibrary.tobiigaze_constants.TOBIIGAZE_DEVICE_INFO_MAX_GENERATION_LENGTH.value()}) 
	@Field(2) 
	public Pointer<Byte > generation() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : char[TOBIIGAZE_DEVICE_INFO_MAX_FIRMWARE_LENGTH]
	@Array({(int)tobiisdk.TobiiSDKLibrary.tobiigaze_constants.TOBIIGAZE_DEVICE_INFO_MAX_FIRMWARE_LENGTH.value()}) 
	@Field(3) 
	public Pointer<Byte > firmware_version() {
		return this.io.getPointerField(this, 3);
	}
	public tobiigaze_device_info() {
		super();
	}
	public tobiigaze_device_info(Pointer pointer) {
		super(pointer);
	}
}
