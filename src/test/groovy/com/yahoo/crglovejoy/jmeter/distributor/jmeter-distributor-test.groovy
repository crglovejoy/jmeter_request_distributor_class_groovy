import com.yahoo.crglovejoy.jmeter.distributor.Distributor
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class DistributorTest {

    static final Distributor distr = new Distributor("1:3,2:4,3:5")
    static final String[] validLabels = ["1", "2", "3"]

    @Test
    void ValidateArrayContent() {
        String[] arrClone = distr.GetDistributionArrayCopy()

        assertEquals(12, arrClone.size(), "Array size should be 12")
        assertEquals(3, arrClone.count { it == "1" }, "Should have 3 labels of 1")
        assertEquals(4, arrClone.count { it == "2" }, "Should have 4 labels of 2")
        assertEquals(5, arrClone.count { it == "3" }, "Should have 5 labels of 3")
    }

    @Test
    void ValidateRandomSelection() {
        int cnt1 = 0
        int cnt2 = 0
        int cnt3 = 0

        for (int i = 0; i < 30; i++) {
            String label = distr.GetRandomDistLabel()
            assertTrue(label in validLabels, "Label should be valid")

            switch(label) {
                case "1":
                    cnt1++
                    break
                case "2":
                    cnt2++
                    break
                case "3":
                    cnt3++
                    break
                default:
                    // shouldn't get here due to the assert above the switch, but...
                    throw new Exception(label + " is not a valid label!!")
            }
        }

        assertTrue(cnt1 > 0, "Should have more than 0 labels of 1 selected")
        assertTrue(cnt2 > 0, "Should have more than 0 labels of 2 selected")
        assertTrue(cnt3 > 0, "Should have more than 0 labels of 3 selected")
    }
}