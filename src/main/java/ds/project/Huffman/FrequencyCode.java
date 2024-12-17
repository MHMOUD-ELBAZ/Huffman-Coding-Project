package ds.project.Huffman;

public class FrequencyCode {
    public int frequency;
    public String code;

    public FrequencyCode(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "FrequencyCode{" + "frequency=" + frequency + ", code=" + code + '}';
    }
}
