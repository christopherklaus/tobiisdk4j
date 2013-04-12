package tobii.lowlevel.sdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct holds eye tracker Calibration Data that is fetched from or sent to the Eye Tracker. The data array holds null <br>
 * terminated strings.<br>
 * @field data The calibration data.<br>
 * @field actual_size The length of the calibration data.<br>
 * <i>native declaration : tobii_gaze_sdk\include\tobiigaze_data_types.h:131</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_calibration extends StructObject {
	/// C type : uint8_t[TOBIIGAZE_CALIBRATION_DATA_CAPACITY]
	@Array({4 * 1024 * 1024}) 
	@Field(0) 
	public Pointer<Byte > data() {
		return this.io.getPointerField(this, 0);
	}
	@Field(1) 
	public int actual_size() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public tobiigaze_calibration actual_size(int actual_size) {
		this.io.setIntField(this, 1, actual_size);
		return this;
	}
	public tobiigaze_calibration() {
		super();
	}
	public tobiigaze_calibration(Pointer pointer) {
		super(pointer);
	}
}
