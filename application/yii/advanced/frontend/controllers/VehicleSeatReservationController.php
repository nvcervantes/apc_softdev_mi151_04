<?php

namespace app\controllers;

use Yii;
use app\models\vehicleseatreservation;
use app\models\VehicleSeatReservationClass;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * VehicleSeatReservationController implements the CRUD actions for vehicleseatreservation model.
 */
class VehicleSeatReservationController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],
        ];
    }

    /**
     * Lists all vehicleseatreservation models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new VehicleSeatReservationClass();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single vehicleseatreservation model.
     * @param integer $vehicle_id
     * @param integer $seat_reservation_id
     * @return mixed
     */
    public function actionView($vehicle_id, $seat_reservation_id)
    {
        return $this->render('view', [
            'model' => $this->findModel($vehicle_id, $seat_reservation_id),
        ]);
    }

    /**
     * Creates a new vehicleseatreservation model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new vehicleseatreservation();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'vehicle_id' => $model->vehicle_id, 'seat_reservation_id' => $model->seat_reservation_id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing vehicleseatreservation model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $vehicle_id
     * @param integer $seat_reservation_id
     * @return mixed
     */
    public function actionUpdate($vehicle_id, $seat_reservation_id)
    {
        $model = $this->findModel($vehicle_id, $seat_reservation_id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'vehicle_id' => $model->vehicle_id, 'seat_reservation_id' => $model->seat_reservation_id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing vehicleseatreservation model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $vehicle_id
     * @param integer $seat_reservation_id
     * @return mixed
     */
    public function actionDelete($vehicle_id, $seat_reservation_id)
    {
        $this->findModel($vehicle_id, $seat_reservation_id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the vehicleseatreservation model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $vehicle_id
     * @param integer $seat_reservation_id
     * @return vehicleseatreservation the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($vehicle_id, $seat_reservation_id)
    {
        if (($model = vehicleseatreservation::findOne(['vehicle_id' => $vehicle_id, 'seat_reservation_id' => $seat_reservation_id])) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
