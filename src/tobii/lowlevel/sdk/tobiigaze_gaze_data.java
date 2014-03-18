package tobii.lowlevel.sdk;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_tracking_status;
/**
 * This struct holds gaze data reveiced from the eye tracker.<br>
 * @field timestamp Timestamp for the gaze data<br>
 * @field tracking_status The combined tracking status for both eyes.<br>
 * @field left Gaze data for the left eye<br>
 * @field right Gaze data for the right eye<br>
 * <i>native declaration : tobii_gaze_sdk/include/tobiigaze_data_types.h:338</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_gaze_data extends StructObject {
	@Field(0) 
	public long timestamp() {
		return this.io.getLongField(this, 0);
	}
	@Field(0) 
	public tobiigaze_gaze_data timestamp(long timestamp) {
		this.io.setLongField(this, 0, timestamp);
		return this;
	}
	/// C type : tobiigaze_tracking_status
	@Field(1) 
	public IntValuedEnum<tobiigaze_tracking_status > tracking_status() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : tobiigaze_tracking_status
	@Field(1) 
	public tobiigaze_gaze_data tracking_status(IntValuedEnum<tobiigaze_tracking_status > tracking_status) {
		this.io.setEnumField(this, 1, tracking_status);
		return this;
	}
	/// C type : tobiigaze_gaze_data_eye
	@Field(2) 
	public tobiigaze_gaze_data_eye left() {
		return this.io.getNativeObjectField(this, 2);
	}
	/// C type : tobiigaze_gaze_data_eye
	@Field(2) 
	public tobiigaze_gaze_data left(tobiigaze_gaze_data_eye left) {
		this.io.setNativeObjectField(this, 2, left);
		return this;
	}
	/// C type : tobiigaze_gaze_data_eye
	@Field(3) 
	public tobiigaze_gaze_data_eye right() {
		return this.io.getNativeObjectField(this, 3);
	}
	/// C type : tobiigaze_gaze_data_eye
	@Field(3) 
	public tobiigaze_gaze_data right(tobiigaze_gaze_data_eye right) {
		this.io.setNativeObjectField(this, 3, right);
		return this;
	}
	public tobiigaze_gaze_data() {
		super();
	}
	public tobiigaze_gaze_data(Pointer pointer) {
		super(pointer);
	}
}
