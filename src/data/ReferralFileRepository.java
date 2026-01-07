package data;

import model.Referral;

import java.util.ArrayList;
import java.util.List;

public class ReferralFileRepository {

    private String filePath;

    public ReferralFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Referral> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Referral> referrals = new ArrayList<>();

        for (String[] r : rows) {
            referrals.add(new Referral(
                    r[0].trim(),
                    r[1].trim(),
                    r[2].trim(),
                    r[3].trim(),
                    r[4].trim(),
                    r[5].trim(),
                    r[6].trim(),
                    r[7].trim(),
                    r[8].trim(),
                    r[9].trim(),
                    r[10].trim(),
                    r[11].trim(),
                    r[12].trim(),
                    r[13].trim(),
                    r[14].trim(),
                    r[15].trim()
            ));
        }

        return referrals;
    }
}
