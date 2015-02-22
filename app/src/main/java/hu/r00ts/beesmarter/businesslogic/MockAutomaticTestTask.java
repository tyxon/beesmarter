package hu.r00ts.beesmarter.businesslogic;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class MockAutomaticTestTask extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    private boolean isOk;

    public MockAutomaticTestTask(Activity activity, String serverIP, String clientId) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Training training = XmlParser.parseTraining("<training><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"36%\" relative-pos-y=\"63%\" /><key-up posix-time=\"85\" code=\"l\" relative-pos-x=\"31%\" relative-pos-y=\"64%\" /><key-down posix-time=\"282\" code=\"i\" relative-pos-x=\"34%\" relative-pos-y=\"82%\" /><key-up posix-time=\"355\" code=\"i\" relative-pos-x=\"37%\" relative-pos-y=\"84%\" /><key-down posix-time=\"518\" code=\"h\" relative-pos-x=\"73%\" relative-pos-y=\"84%\" /><key-up posix-time=\"580\" code=\"h\" relative-pos-x=\"73%\" relative-pos-y=\"84%\" /><key-down posix-time=\"798\" code=\"k\" relative-pos-x=\"36%\" relative-pos-y=\"72%\" /><key-up posix-time=\"871\" code=\"k\" relative-pos-x=\"36%\" relative-pos-y=\"72%\" /><key-down posix-time=\"1154\" code=\"n\" relative-pos-x=\"48%\" relative-pos-y=\"79%\" /><key-up posix-time=\"1238\" code=\"n\" relative-pos-x=\"48%\" relative-pos-y=\"81%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"48%\" relative-pos-y=\"80%\" /><key-up posix-time=\"107\" code=\"l\" relative-pos-x=\"44%\" relative-pos-y=\"80%\" /><key-down posix-time=\"302\" code=\"i\" relative-pos-x=\"63%\" relative-pos-y=\"70%\" /><key-up posix-time=\"398\" code=\"i\" relative-pos-x=\"62%\" relative-pos-y=\"74%\" /><key-down posix-time=\"551\" code=\"h\" relative-pos-x=\"70%\" relative-pos-y=\"83%\" /><key-up posix-time=\"646\" code=\"h\" relative-pos-x=\"70%\" relative-pos-y=\"83%\" /><key-down posix-time=\"842\" code=\"k\" relative-pos-x=\"41%\" relative-pos-y=\"61%\" /><key-up posix-time=\"915\" code=\"k\" relative-pos-x=\"43%\" relative-pos-y=\"66%\" /><key-down posix-time=\"1166\" code=\"n\" relative-pos-x=\"41%\" relative-pos-y=\"78%\" /><key-up posix-time=\"1249\" code=\"n\" relative-pos-x=\"43%\" relative-pos-y=\"79%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"43%\" relative-pos-y=\"74%\" /><key-up posix-time=\"97\" code=\"l\" relative-pos-x=\"38%\" relative-pos-y=\"74%\" /><key-down posix-time=\"281\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"66%\" /><key-up posix-time=\"366\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"67%\" /><key-down posix-time=\"530\" code=\"h\" relative-pos-x=\"70%\" relative-pos-y=\"82%\" /><key-up posix-time=\"604\" code=\"h\" relative-pos-x=\"70%\" relative-pos-y=\"82%\" /><key-down posix-time=\"855\" code=\"k\" relative-pos-x=\"40%\" relative-pos-y=\"51%\" /><key-up posix-time=\"915\" code=\"k\" relative-pos-x=\"40%\" relative-pos-y=\"51%\" /><key-down posix-time=\"1189\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"73%\" /><key-up posix-time=\"1262\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"74%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"59%\" relative-pos-y=\"61%\" /><key-up posix-time=\"75\" code=\"l\" relative-pos-x=\"54%\" relative-pos-y=\"62%\" /><key-down posix-time=\"313\" code=\"i\" relative-pos-x=\"58%\" relative-pos-y=\"61%\" /><key-up posix-time=\"375\" code=\"i\" relative-pos-x=\"58%\" relative-pos-y=\"61%\" /><key-down posix-time=\"573\" code=\"h\" relative-pos-x=\"76%\" relative-pos-y=\"63%\" /><key-up posix-time=\"630\" code=\"h\" relative-pos-x=\"76%\" relative-pos-y=\"63%\" /><key-down posix-time=\"885\" code=\"k\" relative-pos-x=\"41%\" relative-pos-y=\"56%\" /><key-up posix-time=\"936\" code=\"k\" relative-pos-x=\"41%\" relative-pos-y=\"57%\" /><key-down posix-time=\"1252\" code=\"n\" relative-pos-x=\"51%\" relative-pos-y=\"92%\" /><key-up posix-time=\"1314\" code=\"n\" relative-pos-x=\"51%\" relative-pos-y=\"92%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"41%\" relative-pos-y=\"79%\" /><key-up posix-time=\"104\" code=\"l\" relative-pos-x=\"38%\" relative-pos-y=\"77%\" /><key-down posix-time=\"303\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"71%\" /><key-up posix-time=\"375\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"72%\" /><key-down posix-time=\"572\" code=\"h\" relative-pos-x=\"59%\" relative-pos-y=\"78%\" /><key-up posix-time=\"634\" code=\"h\" relative-pos-x=\"59%\" relative-pos-y=\"78%\" /><key-down posix-time=\"873\" code=\"k\" relative-pos-x=\"47%\" relative-pos-y=\"56%\" /><key-up posix-time=\"946\" code=\"k\" relative-pos-x=\"48%\" relative-pos-y=\"60%\" /><key-down posix-time=\"1282\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"78%\" /><key-up posix-time=\"1358\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"79%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"54%\" relative-pos-y=\"77%\" /><key-up posix-time=\"99\" code=\"l\" relative-pos-x=\"52%\" relative-pos-y=\"77%\" /><key-down posix-time=\"337\" code=\"i\" relative-pos-x=\"79%\" relative-pos-y=\"14%\" /><key-up posix-time=\"399\" code=\"i\" relative-pos-x=\"77%\" relative-pos-y=\"16%\" /><key-down posix-time=\"704\" code=\"h\" relative-pos-x=\"40%\" relative-pos-y=\"73%\" /><key-up posix-time=\"754\" code=\"h\" relative-pos-x=\"40%\" relative-pos-y=\"73%\" /><key-down posix-time=\"1048\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"63%\" /><key-up posix-time=\"1100\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"63%\" /><key-down posix-time=\"1547\" code=\"n\" relative-pos-x=\"43%\" relative-pos-y=\"79%\" /><key-up posix-time=\"1630\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"84%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"33%\" relative-pos-y=\"73%\" /><key-up posix-time=\"131\" code=\"l\" relative-pos-x=\"29%\" relative-pos-y=\"73%\" /><key-down posix-time=\"346\" code=\"i\" relative-pos-x=\"59%\" relative-pos-y=\"54%\" /><key-up posix-time=\"398\" code=\"i\" relative-pos-x=\"59%\" relative-pos-y=\"54%\" /><key-down posix-time=\"715\" code=\"h\" relative-pos-x=\"75%\" relative-pos-y=\"58%\" /><key-up posix-time=\"772\" code=\"h\" relative-pos-x=\"75%\" relative-pos-y=\"60%\" /><key-down posix-time=\"1178\" code=\"k\" relative-pos-x=\"27%\" relative-pos-y=\"71%\" /><key-up posix-time=\"1253\" code=\"k\" relative-pos-x=\"27%\" relative-pos-y=\"72%\" /><key-down posix-time=\"1458\" code=\"n\" relative-pos-x=\"58%\" relative-pos-y=\"88%\" /><key-up posix-time=\"1554\" code=\"n\" relative-pos-x=\"58%\" relative-pos-y=\"88%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"62%\" relative-pos-y=\"67%\" /><key-up posix-time=\"94\" code=\"l\" relative-pos-x=\"56%\" relative-pos-y=\"68%\" /><key-down posix-time=\"324\" code=\"i\" relative-pos-x=\"51%\" relative-pos-y=\"51%\" /><key-up posix-time=\"398\" code=\"i\" relative-pos-x=\"51%\" relative-pos-y=\"51%\" /><key-down posix-time=\"659\" code=\"h\" relative-pos-x=\"70%\" relative-pos-y=\"67%\" /><key-up posix-time=\"721\" code=\"h\" relative-pos-x=\"73%\" relative-pos-y=\"70%\" /><key-down posix-time=\"1407\" code=\"k\" relative-pos-x=\"27%\" relative-pos-y=\"62%\" /><key-up posix-time=\"1459\" code=\"k\" relative-pos-x=\"27%\" relative-pos-y=\"62%\" /><key-down posix-time=\"1653\" code=\"n\" relative-pos-x=\"55%\" relative-pos-y=\"75%\" /><key-up posix-time=\"1720\" code=\"n\" relative-pos-x=\"55%\" relative-pos-y=\"75%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"44%\" relative-pos-y=\"84%\" /><key-up posix-time=\"84\" code=\"l\" relative-pos-x=\"38%\" relative-pos-y=\"83%\" /><key-down posix-time=\"314\" code=\"i\" relative-pos-x=\"38%\" relative-pos-y=\"62%\" /><key-up posix-time=\"370\" code=\"i\" relative-pos-x=\"37%\" relative-pos-y=\"68%\" /><key-down posix-time=\"550\" code=\"h\" relative-pos-x=\"66%\" relative-pos-y=\"78%\" /><key-up posix-time=\"623\" code=\"h\" relative-pos-x=\"66%\" relative-pos-y=\"79%\" /><key-down posix-time=\"1025\" code=\"k\" relative-pos-x=\"38%\" relative-pos-y=\"51%\" /><key-up posix-time=\"1089\" code=\"k\" relative-pos-x=\"36%\" relative-pos-y=\"58%\" /><key-down posix-time=\"1373\" code=\"n\" relative-pos-x=\"45%\" relative-pos-y=\"68%\" /><key-up posix-time=\"1445\" code=\"n\" relative-pos-x=\"47%\" relative-pos-y=\"79%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"55%\" relative-pos-y=\"66%\" /><key-up posix-time=\"96\" code=\"l\" relative-pos-x=\"43%\" relative-pos-y=\"63%\" /><key-down posix-time=\"280\" code=\"i\" relative-pos-x=\"59%\" relative-pos-y=\"57%\" /><key-up posix-time=\"386\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"58%\" /><key-down posix-time=\"691\" code=\"h\" relative-pos-x=\"65%\" relative-pos-y=\"84%\" /><key-up posix-time=\"755\" code=\"h\" relative-pos-x=\"66%\" relative-pos-y=\"85%\" /><key-down posix-time=\"1092\" code=\"k\" relative-pos-x=\"30%\" relative-pos-y=\"62%\" /><key-up posix-time=\"1131\" code=\"k\" relative-pos-x=\"30%\" relative-pos-y=\"62%\" /><key-down posix-time=\"1361\" code=\"n\" relative-pos-x=\"56%\" relative-pos-y=\"90%\" /><key-up posix-time=\"1422\" code=\"n\" relative-pos-x=\"56%\" relative-pos-y=\"90%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"43%\" relative-pos-y=\"77%\" /><key-up posix-time=\"117\" code=\"l\" relative-pos-x=\"43%\" relative-pos-y=\"77%\" /><key-down posix-time=\"357\" code=\"i\" relative-pos-x=\"51%\" relative-pos-y=\"68%\" /><key-up posix-time=\"419\" code=\"i\" relative-pos-x=\"51%\" relative-pos-y=\"68%\" /><key-down posix-time=\"701\" code=\"h\" relative-pos-x=\"66%\" relative-pos-y=\"89%\" /><key-up posix-time=\"764\" code=\"h\" relative-pos-x=\"68%\" relative-pos-y=\"91%\" /><key-down posix-time=\"1015\" code=\"k\" relative-pos-x=\"55%\" relative-pos-y=\"54%\" /><key-up posix-time=\"1078\" code=\"k\" relative-pos-x=\"52%\" relative-pos-y=\"55%\" /><key-down posix-time=\"1317\" code=\"n\" relative-pos-x=\"43%\" relative-pos-y=\"80%\" /><key-up posix-time=\"1368\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"83%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"31%\" relative-pos-y=\"65%\" /><key-up posix-time=\"74\" code=\"l\" relative-pos-x=\"31%\" relative-pos-y=\"65%\" /><key-down posix-time=\"324\" code=\"i\" relative-pos-x=\"41%\" relative-pos-y=\"42%\" /><key-up posix-time=\"364\" code=\"i\" relative-pos-x=\"41%\" relative-pos-y=\"42%\" /><key-down posix-time=\"592\" code=\"h\" relative-pos-x=\"72%\" relative-pos-y=\"67%\" /><key-up posix-time=\"656\" code=\"h\" relative-pos-x=\"72%\" relative-pos-y=\"67%\" /><key-down posix-time=\"1255\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"64%\" /><key-up posix-time=\"1326\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"64%\" /><key-down posix-time=\"1488\" code=\"n\" relative-pos-x=\"50%\" relative-pos-y=\"82%\" /><key-up posix-time=\"1563\" code=\"n\" relative-pos-x=\"50%\" relative-pos-y=\"82%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"55%\" relative-pos-y=\"69%\" /><key-up posix-time=\"107\" code=\"l\" relative-pos-x=\"45%\" relative-pos-y=\"72%\" /><key-down posix-time=\"315\" code=\"i\" relative-pos-x=\"66%\" relative-pos-y=\"58%\" /><key-up posix-time=\"387\" code=\"i\" relative-pos-x=\"66%\" relative-pos-y=\"58%\" /><key-down posix-time=\"617\" code=\"h\" relative-pos-x=\"62%\" relative-pos-y=\"70%\" /><key-up posix-time=\"680\" code=\"h\" relative-pos-x=\"65%\" relative-pos-y=\"77%\" /><key-down posix-time=\"951\" code=\"k\" relative-pos-x=\"36%\" relative-pos-y=\"58%\" /><key-up posix-time=\"1001\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"61%\" /><key-down posix-time=\"1319\" code=\"n\" relative-pos-x=\"44%\" relative-pos-y=\"87%\" /><key-up posix-time=\"1380\" code=\"n\" relative-pos-x=\"45%\" relative-pos-y=\"87%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"63%\" relative-pos-y=\"66%\" /><key-up posix-time=\"103\" code=\"l\" relative-pos-x=\"52%\" relative-pos-y=\"66%\" /><key-down posix-time=\"291\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"69%\" /><key-up posix-time=\"342\" code=\"i\" relative-pos-x=\"54%\" relative-pos-y=\"69%\" /><key-down posix-time=\"518\" code=\"h\" relative-pos-x=\"66%\" relative-pos-y=\"74%\" /><key-up posix-time=\"591\" code=\"h\" relative-pos-x=\"63%\" relative-pos-y=\"74%\" /><key-down posix-time=\"995\" code=\"k\" relative-pos-x=\"27%\" relative-pos-y=\"77%\" /><key-up posix-time=\"1067\" code=\"k\" relative-pos-x=\"25%\" relative-pos-y=\"81%\" /><key-down posix-time=\"1362\" code=\"n\" relative-pos-x=\"41%\" relative-pos-y=\"79%\" /><key-up posix-time=\"1423\" code=\"n\" relative-pos-x=\"43%\" relative-pos-y=\"80%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"38%\" relative-pos-y=\"64%\" /><key-up posix-time=\"86\" code=\"l\" relative-pos-x=\"34%\" relative-pos-y=\"65%\" /><key-down posix-time=\"338\" code=\"i\" relative-pos-x=\"52%\" relative-pos-y=\"76%\" /><key-up posix-time=\"411\" code=\"i\" relative-pos-x=\"52%\" relative-pos-y=\"76%\" /><key-down posix-time=\"651\" code=\"h\" relative-pos-x=\"62%\" relative-pos-y=\"87%\" /><key-up posix-time=\"712\" code=\"h\" relative-pos-x=\"63%\" relative-pos-y=\"89%\" /><key-down posix-time=\"1050\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"52%\" /><key-up posix-time=\"1079\" code=\"k\" relative-pos-x=\"37%\" relative-pos-y=\"54%\" /><key-down posix-time=\"1428\" code=\"n\" relative-pos-x=\"52%\" relative-pos-y=\"71%\" /><key-up posix-time=\"1493\" code=\"n\" relative-pos-x=\"52%\" relative-pos-y=\"71%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"38%\" relative-pos-y=\"66%\" /><key-up posix-time=\"102\" code=\"l\" relative-pos-x=\"33%\" relative-pos-y=\"66%\" /><key-down posix-time=\"302\" code=\"i\" relative-pos-x=\"58%\" relative-pos-y=\"52%\" /><key-up posix-time=\"363\" code=\"i\" relative-pos-x=\"58%\" relative-pos-y=\"52%\" /><key-down posix-time=\"604\" code=\"h\" relative-pos-x=\"56%\" relative-pos-y=\"78%\" /><key-up posix-time=\"665\" code=\"h\" relative-pos-x=\"59%\" relative-pos-y=\"83%\" /><key-down posix-time=\"905\" code=\"k\" relative-pos-x=\"44%\" relative-pos-y=\"68%\" /><key-up posix-time=\"978\" code=\"k\" relative-pos-x=\"47%\" relative-pos-y=\"70%\" /><key-down posix-time=\"1270\" code=\"n\" relative-pos-x=\"52%\" relative-pos-y=\"85%\" /><key-up posix-time=\"1377\" code=\"n\" relative-pos-x=\"54%\" relative-pos-y=\"89%\" /></pattern><pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"15%\" relative-pos-y=\"60%\" /><key-up posix-time=\"95\" code=\"l\" relative-pos-x=\"13%\" relative-pos-y=\"61%\" /><key-down posix-time=\"281\" code=\"i\" relative-pos-x=\"44%\" relative-pos-y=\"71%\" /><key-up posix-time=\"354\" code=\"i\" relative-pos-x=\"44%\" relative-pos-y=\"71%\" /><key-down posix-time=\"529\" code=\"h\" relative-pos-x=\"75%\" relative-pos-y=\"80%\" /><key-up posix-time=\"593\" code=\"h\" relative-pos-x=\"75%\" relative-pos-y=\"80%\" /><key-down posix-time=\"830\" code=\"k\" relative-pos-x=\"34%\" relative-pos-y=\"62%\" /><key-up posix-time=\"903\" code=\"k\" relative-pos-x=\"34%\" relative-pos-y=\"65%\" /><key-down posix-time=\"1176\" code=\"n\" relative-pos-x=\"56%\" relative-pos-y=\"79%\" /><key-up posix-time=\"1248\" code=\"n\" relative-pos-x=\"56%\" relative-pos-y=\"79%\" /></pattern></training>");

        PasswordBehaviorChecker passwordBehaviorChecker = new PasswordBehaviorChecker("lihkn", training);

        Pattern testPattern = XmlParser.parsePattern("<pattern><key-down posix-time=\"0\" code=\"l\" relative-pos-x=\"59%\" relative-pos-y=\"63%\" /><key-up posix-time=\"101\" code=\"l\" relative-pos-x=\"50%\" relative-pos-y=\"64%\" /><key-down posix-time=\"282\" code=\"i\" relative-pos-x=\"51%\" relative-pos-y=\"58%\" /><key-up posix-time=\"355\" code=\"i\" relative-pos-x=\"50%\" relative-pos-y=\"60%\" /><key-down posix-time=\"507\" code=\"h\" relative-pos-x=\"68%\" relative-pos-y=\"66%\" /><key-up posix-time=\"591\" code=\"h\" relative-pos-x=\"72%\" relative-pos-y=\"72%\" /><key-down posix-time=\"765\" code=\"k\" relative-pos-x=\"22%\" relative-pos-y=\"61%\" /><key-up posix-time=\"849\" code=\"k\" relative-pos-x=\"19%\" relative-pos-y=\"63%\" /><key-down posix-time=\"1079\" code=\"n\" relative-pos-x=\"58%\" relative-pos-y=\"73%\" /><key-up posix-time=\"1151\" code=\"n\" relative-pos-x=\"58%\" relative-pos-y=\"73%\" /></pattern>");
        isOk = passwordBehaviorChecker.isOk(testPattern);
        Log.d("answer", isOk ? "ACCEPT" : "REJECT");

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(activity, isOk ? "ACCEPT" : "REJECT", Toast.LENGTH_SHORT).show();

        super.onPostExecute(result);
    }

}