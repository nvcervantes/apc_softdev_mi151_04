<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\vehicleseatreservation */

$this->title = $model->vehicle_id;
$this->params['breadcrumbs'][] = ['label' => 'Vehicleseatreservations', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="vehicleseatreservation-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'vehicle_id' => $model->vehicle_id, 'seat_reservation_id' => $model->seat_reservation_id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'vehicle_id' => $model->vehicle_id, 'seat_reservation_id' => $model->seat_reservation_id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'vehicle_id',
            'seat_reservation_id',
            'user_id',
            'time_start',
            'time_end',
            'date',
            'remarks',
        ],
    ]) ?>

</div>
