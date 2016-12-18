package com.papaver.thenewyorktimes.resource;

import android.content.Context;
import android.widget.Toast;

import com.papaver.thenewyorktimes.App;
import com.papaver.thenewyorktimes.R;
import com.papaver.thenewyorktimes.adapter.Item;
import com.papaver.thenewyorktimes.event.DataLoadedEvent;
import com.papaver.thenewyorktimes.http.HttpManager;
import com.papaver.thenewyorktimes.http.model.ApiResponse;
import com.papaver.thenewyorktimes.http.model.MultiMedia;
import com.papaver.thenewyorktimes.http.model.Result;
import com.papaver.thenewyorktimes.resource.model.RealmMultiMedia;
import com.papaver.thenewyorktimes.resource.model.RealmResult;
import com.papaver.thenewyorktimes.resource.model.RealmString;
import com.papaver.thenewyorktimes.util.ConnectivityUtils;
import com.papaver.thenewyorktimes.util.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Office on 2016-12-17.
 */

public class ResourceManager {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    private static ResourceManager sManager = null;

    public static ResourceManager get(Context c) {
        if ( sManager == null ) {
            synchronized ( ResourceManager.class ) {
                if ( sManager == null ) {
                    sManager = new ResourceManager(c);
                }
            }
        }

        return sManager;
    }

    public void clear() {
        mOnTaskListener = null;
        sManager = null;
    }

    // ========================================================================================== //
    private Context mContext;

    private ResourceManager(Context c) {
        init(c);
    }

    private void init(Context c) {
        mContext = c;
    }

    // ========================================================================================== //
    /**
     * 어플리케이션 시작 시 최초에 서버로 데이터 요청.
     */
    public void request() {
        if ( !ConnectivityUtils.isNetworkAvailable() ) {
            Toast.makeText(App.get(), App.get().getString(R.string.network_is_not_available), Toast.LENGTH_SHORT).show();
            return;
        }
        HttpManager.get(App.get()).getContents(mOnTaskListener);
    }

    /**
     * DB에 저장된 내역 있으면 읽음.
     *
     * @return
     */
    public List<Item> read() {
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", read();");
        List<Item> items = new ArrayList<>();

        RealmResults<RealmResult> realmResults = Realm.getDefaultInstance()
                .where(RealmResult.class)
                .findAllSorted("mUpdatedTimeStamp", Sort.DESCENDING);

        items.addAll( realmResults );
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", read(), size = " + realmResults.size());

        return items;
    }

    // ========================================================================================== //
    private void printResults(RealmResults<RealmResult> realmResults) {
        for ( RealmResult realmResult : realmResults ) {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", read(), title = " + realmResult.getTitle());
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", read(), update, date = " + realmResult.getUpdated_date());
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", read(), update, time = " + realmResult.getUpdatedTimeStamp());
        }
    }

    // ========================================================================================== //
    /**
     * Http 요청 체크 리스너.
     * 요청이 성공하면 DB에 저장하고 load() 호출.
     * 요청이 실패하면 바로 load() 호출.
     */
    private HttpManager.OnHttpTaskListener mOnTaskListener = new HttpManager.OnHttpTaskListener() {
        @Override
        public void onTaskSuccess(ApiResponse apiResponse) {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onTaskListener(), success, time = " + apiResponse.getLastUpdated());
            List<Result> results = apiResponse.getResults();
            if ( results == null || results.size() == 0 ) {
                return;
            }

            final RealmList<RealmResult> realmResults =
                    convertResultsToRealmResults(results);

            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(realmResults);
                }
            });

            Realm.getDefaultInstance().close();
            load(true);
        }

        @Override
        public void onTaskFailure() {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onTaskListener(), fail;");
            load(false);
        }
    };

    // ========================================================================================== //
    /**
     * 서버로부터 받은 데이터 저장 완료되면 UI 쓰레드에 전달하기 위해 로드.
     * 서버 작업 실패한 경우에는 바로 이벤트 버스 호출.
     *
     * @param isSuccess
     */
    private void load(boolean isSuccess) {
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", load();");
        List<Item> items = null;
        if ( !isSuccess ) {
            EventBus.getDefault().post(new DataLoadedEvent(isSuccess, items));
            return;
        }

        RealmResults<RealmResult> realmResults = Realm.getDefaultInstance()
                .where(RealmResult.class)
                .findAllSorted("mUpdatedTimeStamp", Sort.DESCENDING);

        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", load(), size of results = " + realmResults.size());
        items = new ArrayList<>();
        items.addAll(realmResults);

        EventBus.getDefault().post(new DataLoadedEvent(isSuccess, items));
    }


    // ========================================================================================== //
    /**
     * Realm과 POJO(for retrofit)의 변환을 위한 메소드.
     */
    private RealmList<RealmResult> convertResultsToRealmResults(List<Result> results) {
        if ( results == null || results.size() == 0 ) {
            return null;
        }

        RealmList<RealmResult> realmResults = new RealmList<>();
        RealmResult realmResult;
        for ( Result result : results ) {
            if ( result == null ) {
                continue;
            }

            realmResult = new RealmResult();
            realmResult.setSection( result.getSection() );
            realmResult.setSubSection( result.getSubSection() );
            realmResult.setTitle( result.getTitle() );
            realmResult.setAbstract( result.getAbstract() );
            realmResult.setUrl( result.getUrl() );
            realmResult.setByLine( result.getByLine() );
            realmResult.setItemType( result.getItemType() );
            realmResult.setUpdated_date( result.getUpdated_date() );
            realmResult.setCreatedDate( result.getCreatedDate() );
            realmResult.setPublishedDate( result.getPublishedDate() );
            realmResult.setMaterialTypeFacet( result.getMaterialTypeFacet() );
            realmResult.setKicker( result.getKicker() );

            realmResult.setDesFacet( convertListStringToListRealmString(result.getDesFacet()) );
            realmResult.setOrgFacet( convertListStringToListRealmString(result.getOrgFacet()) );
            realmResult.setPerFacet( convertListStringToListRealmString(result.getPerFacet()) );
            realmResult.setGeoFacet( convertListStringToListRealmString(result.getGeoFacet()) );

            realmResult.setMultiMedias( convertListMediaToListRealmMedia(result.getMultiMedias()) );

            realmResult.setShortUrl( result.getShortUrl() );
            realmResult.setUpdatedTimeStamp( result.getUpdated_date() );

            realmResults.add( realmResult );
        }

        return realmResults;
    }

    // ========================================================================================== //
    private RealmList<RealmString> convertListStringToListRealmString(List<String> strings) {
        RealmList<RealmString> realmStrings = new RealmList<>();
        if ( strings == null || strings.size() == 0 ) {
            return realmStrings;
        }

        for ( String string : strings ) {
            if ( string != null ) {
                realmStrings.add ( convertStringToRealmString(string) );
            }
        }

        return realmStrings;
    }

    private RealmString convertStringToRealmString(String str) {
        RealmString realmString = new RealmString();
        realmString.value = str;
        return realmString;
    }

    // ========================================================================================== //
    private RealmList<RealmMultiMedia> convertListMediaToListRealmMedia(List<MultiMedia> medias) {
        RealmList<RealmMultiMedia> realmList = new RealmList<>();
        if ( medias == null || medias.size() == 0 ) {
            return realmList;
        }

        RealmMultiMedia realmMedia;
        for ( MultiMedia media : medias ) {
            realmMedia = convertMediaToRealmMedia(media);
            if ( realmMedia != null ) {
                realmList.add( realmMedia );
            }
        }

        return realmList;
    }

    private RealmMultiMedia convertMediaToRealmMedia(MultiMedia media) {
        if ( media == null ) {
            return null;
        }

        RealmMultiMedia realmMedia = new RealmMultiMedia();

        realmMedia.setUrl( media.getUrl() );
        realmMedia.setFormat( media.getFormat() );
        realmMedia.setHeight( media.getHeight() );
        realmMedia.setWidth( media.getWidth() );
        realmMedia.setType( media.getType() );
        realmMedia.setSubType( media.getSubType() );
        realmMedia.setCaption( media.getCaption() );
        realmMedia.setCopyright( media.getCopyright() );

        return realmMedia;
    }

    // ========================================================================================== //
}
