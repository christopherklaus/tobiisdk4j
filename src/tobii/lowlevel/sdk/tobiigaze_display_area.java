package tobii.lowlevel.sdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct holds a display area<br>
 * @field top_left <br>
 * @field top_right<br>
 * @field bottom_left<br>
 * <i>native declaration : tobii_gaze_sdk\include\tobiigaze_data_types.h:271</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_display_area extends StructObject {
	/// C type : tobiigaze_point_3d
	@Field(0) 
	public tobiigaze_point_3d top_left() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : tobiigaze_point_3d
	@Field(0) 
	public tobiigaze_display_area top_left(tobiigaze_point_3d top_left) {
		this.io.setNativeObjectField(this, 0, top_left);
		return this;
	}
	/// C type : tobiigaze_point_3d
	@Field(1) 
	public tobiigaze_point_3d top_right() {
		return this.io.getNativeObjectField(this, 1);
	}
	/// C type : tobiigaze_point_3d
	@Field(1) 
	public tobiigaze_display_area top_right(tobiigaze_point_3d top_right) {
		this.io.setNativeObjectField(this, 1, top_right);
		return this;
	}
	/// C type : tobiigaze_point_3d
	@Field(2) 
	public tobiigaze_point_3d bottom_left() {
		return this.io.getNativeObjectField(this, 2);
	}
	/// C type : tobiigaze_point_3d
	@Field(2) 
	public tobiigaze_display_area bottom_left(tobiigaze_point_3d bottom_left) {
		this.io.setNativeObjectField(this, 2, bottom_left);
		return this;
	}
	public tobiigaze_display_area() {
		super();
	}
	public tobiigaze_display_area(Pointer pointer) {
		super(pointer);
	}
}
