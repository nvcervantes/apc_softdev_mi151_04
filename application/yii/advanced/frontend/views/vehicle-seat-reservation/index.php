<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\VehicleSeatReservationClass */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Vehicleseatreservations';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="vehicleseatreservation-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Vehicleseatreservation', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'vehicle_id',
            'seat_reservation_id',
            'user_id',
            'time_start',
            'time_end',
            // 'date',
            // 'remarks',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
