package tobii.lowlevel.sdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : tobii_gaze_sdk/include/tobiigaze_data_types.h:538</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_authorize_challenge extends StructObject {
	@Field(0) 
	public int realm_id() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public tobiigaze_authorize_challenge realm_id(int realm_id) {
		this.io.setIntField(this, 0, realm_id);
		return this;
	}
	@Field(1) 
	public int algorithm() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public tobiigaze_authorize_challenge algorithm(int algorithm) {
		this.io.setIntField(this, 1, algorithm);
		return this;
	}
	/// C type : uint8_t[TOBIIGAZE_AUTHORIZE_CHALLENGE_MAX_LEN]
	@Array({4 * 1024 * 1024}) 
	@Field(2) 
	public Pointer<Byte > challenge() {
		return this.io.getPointerField(this, 2);
	}
	@Field(3) 
	public int actual_size() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public tobiigaze_authorize_challenge actual_size(int actual_size) {
		this.io.setIntField(this, 3, actual_size);
		return this;
	}
	public tobiigaze_authorize_challenge() {
		super();
	}
	public tobiigaze_authorize_challenge(Pointer pointer) {
		super(pointer);
	}
}