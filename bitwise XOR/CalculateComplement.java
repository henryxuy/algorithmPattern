import java.lang.Math;

class CalculateComplement {
    public static int bitwiseComplement(int num) {
        // count number of total bits in n
        int bitCount = 0;
        int n = num;
        while(n > 0){
            bitCount++;
            n = n >> 1;            // shift to right
        }
        // 11111...1 (all bits set)
        int all_bits_set = (int) Math.pow(2, bitCount) - 1;

        return num ^ all_bits_set;
    }

    public static void main( String args[] ) {
        System.out.println("Bitwise complement is: " + CalculateComplement.bitwiseComplement(8));
        System.out.println("Bitwise complement is: " + CalculateComplement.bitwiseComplement(10));
    }
}
